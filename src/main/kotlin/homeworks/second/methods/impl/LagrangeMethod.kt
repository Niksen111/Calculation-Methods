package homeworks.second.methods.impl

import homeworks.second.methods.InterpolationMethod
import homeworks.utils.Polynomial
import java.math.BigDecimal
import java.util.SortedMap

class LagrangeMethod : InterpolationMethod {
    override var table: SortedMap<BigDecimal, BigDecimal> = sortedMapOf()
        private set

    override var degree: Int = 0
        private set

    var polynomial: Polynomial = Polynomial(arrayOf(BigDecimal.ZERO))
        private set

    override fun setUp(table: SortedMap<BigDecimal, BigDecimal>, degree: Int) {
        this.table = table
        this.degree = degree
        this.polynomial = createPolynomial()
    }

    private fun createPolynomial(): Polynomial {
        var result = Polynomial(arrayOf(BigDecimal.ZERO))
        table.map { entry ->
            var lI = Polynomial(arrayOf(entry.value))
            val xI = entry.key
            var divide = BigDecimal.ONE
            table.map {
                if (it.key != xI) {
                    val xJ = it.key
                    divide *= (xI - xJ)
                    lI *= Polynomial(arrayOf(-xJ, 1.0.toBigDecimal()))
                }
            }

            lI *= Polynomial(arrayOf(BigDecimal.ONE / divide))
            result += lI
        }

        return result
    }

    override fun evaluate(point: BigDecimal): BigDecimal {
        return polynomial.evaluate(point)
    }

    override fun getMethodName() = methodName

    companion object {
        const val methodName = "Lagrange"
    }
}