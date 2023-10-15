package homeworks.first.methods

import homeworks.utils.vo.Seq
import homeworks.first.utils.vo.SolutionInfo
import java.math.BigDecimal

interface Method {
    fun findSolution(
        func: (BigDecimal) -> (BigDecimal),
        sequence: Seq,
        epsilon: BigDecimal,
        firstDerivative: (BigDecimal) -> (BigDecimal),
        secondDerivative: (BigDecimal) -> (BigDecimal)
    ): SolutionInfo

    fun getMethodName(): String
}