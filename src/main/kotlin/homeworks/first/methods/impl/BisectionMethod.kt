package homeworks.first.methods.impl

import homeworks.first.methods.Method
import homeworks.first.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import kotlin.math.abs

class BisectionMethod : Method {
    override fun findSolution(
        func: (Double) -> Double,
        sequence: Seq,
        epsilon: Double,
        firstDerivative: (Double) -> Double,
        secondDerivative: (Double) -> Double
    ): SolutionInfo {

        var counter = 0
        var currSeq = sequence

        while (currSeq.right - currSeq.left >= 2 * epsilon) {
            val middle = (currSeq.left + currSeq.right) / 2

            currSeq = if (func(middle) * func(currSeq.right) <= 0) {
                Seq(middle, currSeq.right)
            } else {
                Seq(currSeq.left, middle)
            }

            ++counter
        }

        val solution = (currSeq.left + currSeq.right) / 2

        return SolutionInfo(
            getMethodName(),
            solution,
            epsilon,
            abs(func(solution)),
            counter
        )
    }

    override fun getMethodName() = "Bisection"
}