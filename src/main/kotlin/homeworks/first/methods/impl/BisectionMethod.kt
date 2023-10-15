package homeworks.first.methods.impl

import homeworks.first.methods.Method
import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import java.math.BigDecimal

class BisectionMethod : Method {
    override fun findSolution(
        func: (BigDecimal) -> BigDecimal,
        sequence: Seq,
        epsilon: BigDecimal,
        firstDerivative: (BigDecimal) -> BigDecimal,
        secondDerivative: (BigDecimal) -> BigDecimal
    ): SolutionInfo {
        if (func(sequence.left) == BigDecimal.ZERO) {
            return SolutionInfo(
                getMethodName(),
                sequence.left,
                BigDecimal.ZERO,
                func(sequence.left).abs(),
                0
            )
        }

        var counter = 0
        var currSeq = sequence

        while (currSeq.right.minus(currSeq.left) >= BigDecimal.valueOf(2).multiply(epsilon)) {
            val middle = (currSeq.left + currSeq.right).divide(2.0.toBigDecimal())

            currSeq = if (func(middle).multiply(func(currSeq.right)) <= BigDecimal.ZERO) {
                Seq(middle, currSeq.right)
            } else {
                Seq(currSeq.left, middle)
            }

            ++counter
        }

        val solution = (currSeq.left + currSeq.right).divide(2.0.toBigDecimal())

        return SolutionInfo(
            getMethodName(),
            solution,
            epsilon,
            func(solution).abs(),
            counter
        )
    }

    override fun getMethodName() = "Bisection"
}