package homeworks.first.utils.vo

import java.math.BigDecimal

data class SolutionInfo(
    val methodName: String,
    val result: BigDecimal,
    val seqLength: BigDecimal,
    val absoluteDiscrepancy: BigDecimal,
    val stepsNumber: Int
)
