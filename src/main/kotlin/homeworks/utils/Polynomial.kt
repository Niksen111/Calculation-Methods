package homeworks.utils

import java.math.BigDecimal

class Polynomial(
    private var coefficients: Array<BigDecimal>
) {
    private val size: Int
    private val older: BigDecimal

    init {
        require(coefficients.isNotEmpty()) { "Coefficients must not be empty" }
        this.coefficients = coefficients.copyOfRange(0, getExp(coefficients) + 1)
        size = coefficients.size
        older = coefficients.last()
    }

    private fun getExp(array: Array<BigDecimal>): Int {
        return array.foldIndexed(0) { index, acc, d ->
            if (d != BigDecimal.ZERO) index else acc
        }
    }

    operator fun plus(other: Polynomial): Polynomial {
        val max: Array<BigDecimal>
        val min: Array<BigDecimal>
        if (size >= other.size) {
            max = coefficients
            min = other.coefficients
        } else {
            max = other.coefficients
            min = coefficients
        }

        val resultCoefficients = Array<BigDecimal>(max.size) { BigDecimal.ZERO }
        for (i in min.indices) {
            resultCoefficients[i] = min[i]
        }

        for (i in max.indices) {
            resultCoefficients[i] += max[i]
        }

        return Polynomial(resultCoefficients)
    }

    operator fun minus(other: Polynomial): Polynomial {
        val max: Array<BigDecimal>
        val min: Array<BigDecimal>
        if (size > other.size || (size == other.size && older >= other.older)) {
            max = coefficients
            min = other.coefficients
        } else {
            max = other.coefficients
            min = coefficients
        }

        val resultCoefficients = Array<BigDecimal>(max.size) { BigDecimal.ZERO }
        min.forEachIndexed { i, d ->
            resultCoefficients[i] = max[i] - d
        }

        return Polynomial(resultCoefficients.copyOfRange(0, getExp(resultCoefficients)))
    }

    operator fun times(other: Polynomial): Polynomial {
        val product = Array<BigDecimal>(getExp(coefficients) + getExp(other.coefficients) + 1) { BigDecimal.ZERO }
        for (i in coefficients.indices) {
            for (j in other.coefficients.indices) {
                product[i + j] += coefficients[i] * other.coefficients[j]
            }
        }

        return Polynomial(product)
    }

    fun evaluate(x: BigDecimal): BigDecimal {
        var result = BigDecimal.ZERO
        var currentExp = BigDecimal.ONE
        for (i in coefficients.indices) {
            result += coefficients[i] * currentExp
            currentExp *= x
        }

        return result
    }
    
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null) {
            return false
        }

        if (other is Polynomial) {
            this.coefficients.contentEquals(other.coefficients)
        }

        return false
    }

    override fun hashCode(): Int {
        return coefficients.contentHashCode()
    }

    override fun toString(): String {
        var str = ""
        for (i in coefficients.indices.reversed()) {
            if (coefficients[i] == BigDecimal.ZERO) continue
            val plus = if (coefficients[i] >= BigDecimal.ZERO && coefficients.size > 1) "+" else ""
            val degree = if (i > 0) "x^($i)" else ""
            str = str + plus + coefficients[i] + degree
        }

        return str
    }
}