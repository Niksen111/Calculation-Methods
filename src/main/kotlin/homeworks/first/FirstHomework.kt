package homeworks.first

import homeworks.first.methods.MethodsManager
import homeworks.first.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo

/**
 * @author nksenchik
 * @since 1.0.0
 */
class FirstHomework(
    var func: (Double) -> (Double),
    var seq: Seq,
    var epsilon: Double,
    var firstDerivative: (Double) -> (Double),
    var secondDerivative: (Double) -> (Double)
) {
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
        val methodsNames = listOf("Bisection", "Newton", "Modified Newton", "Secant")
    }
}