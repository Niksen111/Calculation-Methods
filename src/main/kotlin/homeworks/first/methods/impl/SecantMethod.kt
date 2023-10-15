package homeworks.first.methods.impl

import homeworks.first.methods.Method
import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import java.math.BigDecimal
import kotlin.math.abs

class SecantMethod : Method {
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

        var previousSolution: BigDecimal = sequence.left

        var currentSolution: BigDecimal = sequence.right

        var nextSolution: BigDecimal =
            currentSolution - func(currentSolution) / (func(currentSolution) - func(
                previousSolution
            )) * (currentSolution - previousSolution)
        var counter = 0

        while ((currentSolution - previousSolution).abs() >= epsilon) {
            previousSolution = currentSolution
            currentSolution = nextSolution
            nextSolution =
                currentSolution - func(currentSolution) / (func(currentSolution) - func(
                    previousSolution
                )) * (currentSolution - previousSolution)
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

    override fun getMethodName() = "Secant"
}