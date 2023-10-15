package homeworks.second.methods

import java.math.BigDecimal
import java.util.SortedMap

interface InterpolationMethod {
    val table: Map<BigDecimal, BigDecimal>

    val degree: Int

    fun setUp(table: SortedMap<BigDecimal, BigDecimal>, degree: Int)

    fun evaluate(
        point: BigDecimal
    ): BigDecimal

    fun getMethodName(): String
}