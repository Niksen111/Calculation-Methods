package homeworks.first.utils.vo

data class SolutionInfo(
    val methodName: String,
    val result: Double,
    val seqLength: Double,
    val absoluteDiscrepancy: Double,
    val stepsNumber: Int
)
