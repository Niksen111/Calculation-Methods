package homeworks.first.methods.impl

import homeworks.first.methods.Method
import homeworks.first.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import java.util.*
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

        val rand = Random(System.currentTimeMillis())

        var previousSolution: Double = sequence.left + rand.nextDouble() * (sequence.right - sequence.left)
        while (secondDerivative(previousSolution) * func(previousSolution) <= 0) {
            previousSolution = sequence.left + rand.nextDouble() * (sequence.right - sequence.left)
        }

        val currSol: Double = if
            (func(previousSolution) * func(sequence.left) >= 0) sequence.right
            else sequence.left
        var currentSolution: Double = previousSolution
        previousSolution = currSol

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