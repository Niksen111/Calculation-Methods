package homeworks.utils

class Polynomial(
    private var coefficients: DoubleArray
) {
    private val size: Int
    private val older: Double

    init {
        require(coefficients.isNotEmpty()) { "Polynomial does not exist" }
        this.coefficients = coefficients.copyOfRange(0, getExp(coefficients))
        size = coefficients.size
        older = coefficients.last()
    }

    private fun getExp(array: DoubleArray): Int {
        return array.foldIndexed(0) { index, acc, d ->
            if (d != 0.0) acc else index
        }
    }

    operator fun plus(other: Polynomial): Polynomial {
        val max: DoubleArray
        val min: DoubleArray
        if (size >= other.size) {
            max = coefficients
            min = other.coefficients
        } else {
            max = other.coefficients
            min = coefficients
        }

        val resultCoefficients = DoubleArray(max.size)
        for (i in min.indices) {
            resultCoefficients[i] = min[i]
        }

        for (i in max.indices) {
            resultCoefficients[i] += max[i]
        }

        return Polynomial(resultCoefficients)
    }

    operator fun minus(other: Polynomial): Polynomial {
        val max: DoubleArray
        val min: DoubleArray
        if (size > other.size || (size == other.size && older >= other.older)) {
            max = coefficients
            min = other.coefficients
        } else {
            max = other.coefficients
            min = coefficients
        }

        val resultCoefficients = DoubleArray(max.size)
        min.forEachIndexed { i, d ->
            resultCoefficients[i] = max[i] - d
        }

        return Polynomial(resultCoefficients.copyOfRange(0, getExp(resultCoefficients)))
    }

    operator fun times(other: Polynomial): Polynomial {
        val product = DoubleArray(getExp(coefficients) + getExp(other.coefficients) + 1)
        for (i in coefficients.indices) {
            for (j in other.coefficients.indices) {
                product[i + j] += coefficients[i] * other.coefficients[j]
            }
        }

        return Polynomial(product)
    }

    fun evaluate(x: Double): Double {
        var result = 0.0
        var currentExp = 1.0
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
            if (coefficients[i] == 0.0) continue
            val plus = if (coefficients[i] > 0 && str.isEmpty()) "+" else ""
            val degree = if (i > 0) "x^($i)" else ""
            str = str + plus + coefficients[i] + degree
        }

        return str
    }
}