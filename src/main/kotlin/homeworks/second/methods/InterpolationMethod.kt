package homeworks.second.methods

interface InterpolationMethod {
    val table: Map<Double, Double>

    val degree: Int

    fun setUp(table: Map<Double, Double>, degree: Int)

    fun evaluate(
        point: Double
    ): Double

    fun getMethodName(): String
}