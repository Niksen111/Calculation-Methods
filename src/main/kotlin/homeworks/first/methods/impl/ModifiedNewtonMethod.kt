package homeworks.first.methods.impl

import homeworks.first.methods.Method
import homeworks.first.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import java.util.*
import kotlin.math.abs

class ModifiedNewtonMethod : Method {
    override fun findSolution(
        func: (Double) -> Double,
        sequence: Seq,
        epsilon: Double,
        firstDerivative: (Double) -> Double,
        secondDerivative: (Double) -> Double
    ): SolutionInfo {
        val rand = Random(System.currentTimeMillis())
        var counter = 0

        var previousSolution: Double = sequence.left + rand.nextDouble() * (sequence.right - sequence.left)
        while (secondDerivative(previousSolution) * func(previousSolution) <= 0) previousSolution =
            sequence.left + rand.nextDouble() * (sequence.right - sequence.left)

        val constDerivative: Double = firstDerivative(previousSolution)

        var currentSolution: Double = previousSolution - func(previousSolution) / constDerivative

        while (abs(currentSolution - previousSolution) >= epsilon) {
            previousSolution = currentSolution
            currentSolution = previousSolution - func(previousSolution) / constDerivative
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

    override fun getMethodName() = "Modified_Newton"
}