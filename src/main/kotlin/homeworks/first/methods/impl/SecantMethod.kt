package homeworks.first.methods.impl

import homeworks.first.methods.Method
import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import kotlin.math.abs

class SecantMethod : Method {
    override fun findSolution(
        func: (Double) -> Double,
        sequence: Seq,
        epsilon: Double,
        firstDerivative: (Double) -> Double,
        secondDerivative: (Double) -> Double
    ): SolutionInfo {
        if (func(sequence.left) == 0.0) {
            return SolutionInfo(
                getMethodName(),
                sequence.left,
                0.0,
                abs(func(sequence.left)),
                0
                )
        }

        var previousSolution: Double = sequence.left

        var currentSolution: Double = sequence.right

        var nextSolution: Double =
            currentSolution - func(currentSolution) / (func(currentSolution) - func(
                previousSolution
            )) * (currentSolution - previousSolution)
        var counter = 0

        while (abs(currentSolution - previousSolution) >= epsilon) {
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
            0.0,
            abs(func(currentSolution)),
            counter
        )
    }

    override fun getMethodName() = "Secant"
}