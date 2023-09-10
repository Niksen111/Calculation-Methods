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
        val rand = Random(System.currentTimeMillis())

        var previousSolution: Double = sequence.left + rand.nextFloat() * (sequence.right - sequence.left)
        while (secondDerivative(previousSolution) * func(previousSolution) <= 0) previousSolution =
            sequence.left + rand.nextFloat() * (sequence.right - sequence.left)

        var currentSolution: Double = sequence.left + rand.nextFloat() * (sequence.right - sequence.left)

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
            func(currentSolution),
            counter
        )
    }

    override fun getMethodName() = "Secant"
}