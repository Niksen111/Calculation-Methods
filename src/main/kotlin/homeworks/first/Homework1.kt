package homeworks.first

import homeworks.first.methods.MethodsManager
import homeworks.first.utils.vo.HomeworkData
import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import kotlin.math.pow

/**
 * @author nksenchik
 * @since 1.0.0
 */
class Homework1(
    val func: (Double) -> (Double),
    val seq: Seq,
    val epsilon: Double,
    val firstDerivative: (Double) -> (Double),
    val secondDerivative: (Double) -> (Double)
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

        val tabulationStep = (seq.right - seq.left) / subsegmentsNumber
        var currSeq = Seq(seq.left, seq.left + tabulationStep)
        val sequences = ArrayList<Seq>()

        for (i in 1..subsegmentsNumber) {
            val y1 = func(currSeq.left)
            val y2 = func(currSeq.right)
            if (y1 * y2 < 0) {
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
            1.2 * x.pow(4) + 2 * x.pow(3) - 13 * x.pow(2) - 14.2 * x - 24.1
            },
            Seq(-5.0, 5.0),
            1.0E-6,
            { x ->
                4.8 * x.pow(3) + 6 * x.pow(2) - 26 * x - 14.2
            },
            { x ->
                14.4 * x.pow(2) + 12 * x - 26
            }
        )
    }
}