package homeworks.first.methods.impl

import homeworks.first.methods.Method
import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import java.math.BigDecimal
import java.util.*

class ModifiedNewtonMethod : Method {
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

        val rand = Random(System.currentTimeMillis())
        var counter = 0

        var previousSolution: BigDecimal = sequence.left + rand.nextDouble().toBigDecimal() * (sequence.right - sequence.left)
        while (secondDerivative(previousSolution) * func(previousSolution) <= BigDecimal.ZERO) {
            previousSolution = sequence.left + rand.nextDouble().toBigDecimal() * (sequence.right - sequence.left)
        }

        val constDerivative: BigDecimal = firstDerivative(previousSolution)

        var currentSolution: BigDecimal = previousSolution - func(previousSolution) / constDerivative

        while ((currentSolution - previousSolution).abs() >= epsilon) {
            previousSolution = currentSolution
            currentSolution = previousSolution - func(previousSolution) / constDerivative
            ++counter
        }

        return SolutionInfo(
            getMethodName(),
            currentSolution,
            BigDecimal.ZERO,
            func(currentSolution).abs(),
            counter
        )
    }

    override fun getMethodName() = "Modified_Newton"
}