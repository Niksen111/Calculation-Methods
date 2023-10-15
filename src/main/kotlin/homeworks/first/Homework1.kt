package homeworks.first

import homeworks.first.methods.MethodsManager
import homeworks.first.utils.vo.HomeworkData
import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import java.math.BigDecimal

/**
 * @author nksenchik
 * @since 1.0.0
 */
class Homework1(
    val func: (BigDecimal) -> (BigDecimal),
    val seq: Seq,
    val epsilon: BigDecimal,
    val firstDerivative: (BigDecimal) -> (BigDecimal),
    val secondDerivative: (BigDecimal) -> (BigDecimal)
) {
    constructor(homeworkData: HomeworkData) : this(
        homeworkData.func,
        homeworkData.seq,
        homeworkData.epsilon,
        homeworkData.firstDerivative,
        homeworkData.secondDerivative
    )
    constructor() : this(defaultData)

    private val methodsManager = MethodsManager()

    fun separateSolutions(subsegmentsNumber: Int): List<Seq> {
        require(subsegmentsNumber > 1) { "Subsegments number must be more the 1" }

        val tabulationStep = (seq.right - seq.left).divide(subsegmentsNumber.toBigDecimal())
        var currSeq = Seq(seq.left, seq.left + tabulationStep)
        val sequences = ArrayList<Seq>()

        for (i in 1..subsegmentsNumber) {
            val y1 = func(currSeq.left)
            val y2 = func(currSeq.right)
            if (y1 * y2 < BigDecimal.ZERO) {
                sequences.add(currSeq)
            }

            currSeq = Seq(currSeq.right, currSeq.right + tabulationStep)
        }

        return sequences
    }

    fun findSolutionByMethod(methodName: String, sequence: Seq): SolutionInfo {
        require(methodName in methodsNames) { "Method name must be in Methods list" }

        val method = methodsManager.getMethod(methodName)
        return method.findSolution(func, sequence, epsilon, firstDerivative, secondDerivative)
    }

    companion object {
        val methodsNames = listOf("Bisection", "Newton", "Modified_Newton", "Secant")
        val defaultData = HomeworkData({ x ->
            1.2.toBigDecimal() * x.pow(4) + 2.0.toBigDecimal() * x.pow(3) -
                13.0.toBigDecimal() * x.pow(2) - 14.2.toBigDecimal() * x - 24.1.toBigDecimal()
            },
            Seq((-5.0).toBigDecimal(), 5.0.toBigDecimal()),
            1.0E-6.toBigDecimal(),
            { x ->
                4.8.toBigDecimal() * x.pow(3) + 6.0.toBigDecimal() * x.pow(2) -
                    26.0.toBigDecimal() * x - 14.2.toBigDecimal()
            },
            { x ->
                14.4.toBigDecimal() * x.pow(2) + 12.0.toBigDecimal() * x - 26.0.toBigDecimal()
            }
        )
    }
}