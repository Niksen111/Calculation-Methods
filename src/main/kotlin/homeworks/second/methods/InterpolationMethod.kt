package homeworks.second.methods

import java.util.SortedMap

interface InterpolationMethod {
    val table: Map<Double, Double>

    val degree: Int

    fun setUp(table: SortedMap<Double, Double>, degree: Int)

    fun evaluate(
        point: Double
    ): Double

    fun getMethodName(): String
}