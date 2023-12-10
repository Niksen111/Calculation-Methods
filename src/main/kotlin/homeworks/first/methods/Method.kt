package homeworks.first.methods

import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo

interface Method {
    fun findSolution(
        func: (Double) -> (Double),
        sequence: Seq,
        epsilon: Double,
        firstDerivative: (Double) -> (Double) = { 1.0 },
        secondDerivative: (Double) -> (Double) = { 1.0 }
    ): SolutionInfo

    fun getMethodName(): String
}