package homeworks.second.methods.impl

import homeworks.second.methods.InterpolationMethod
import homeworks.utils.Polynomial
import java.math.BigDecimal
import java.util.SortedMap

class NewtonMethod : InterpolationMethod {
    override var table: SortedMap<BigDecimal, BigDecimal> = sortedMapOf()
        private set

    override var degree: Int = 0
        private set

    private var polyElements: MutableList<List<Polynomial>> = mutableListOf()

    override fun setUp(table: SortedMap<BigDecimal, BigDecimal>, degree: Int) {
        this.table = table
        this.degree = degree
        this.polyElements = createPolynomial()
    }

    private fun createPolynomial(): MutableList<List<Polynomial>> {
        val elems: MutableList<List<Polynomial>> = mutableListOf()
        table.map { entry ->
            val result = mutableListOf(Polynomial(arrayOf(calcDividedDifference(entry.key))))

            table.filter {
                it.key < entry.key
            }.map {
                result.add(Polynomial(arrayOf ( -(it.key), BigDecimal.valueOf(1.0)) ))
            }

            elems.add(result)
        }

        return elems
    }

    override fun evaluate(point: BigDecimal): BigDecimal {
        var result = BigDecimal.ZERO
        polyElements.map { listPolynomial ->
            var temp = BigDecimal.ONE

            listPolynomial.map {
                temp *= it.evaluate(point)
            }

            result += temp
        }

        return result
    }

    private fun calcDividedDifference(x: BigDecimal): BigDecimal {
        var result = BigDecimal.ZERO

        val tablePart = table.filter {
            it.key <= x
        }

        tablePart.map { entry ->
            var product = BigDecimal.ONE
            tablePart.map {
                if (entry.key != it.key) {
                    product *= entry.key - it.key
                }
            }

            result += entry.value / product
        }

        return result
    }

    override fun getMethodName() = methodName

    override fun toString(): String {
        var str = ""
        var flagSum = false
        for (listPoly in polyElements) {
            flagSum = true
            var temp = ""
            var flagMult = false
            for (poly in listPoly) {
                flagMult = true
                temp += "($poly)*"
            }
            if (flagMult) temp += "1"
            str += "$temp+"
        }

        if (flagSum) str += "0"

        return str
    }

    companion object {
        const val methodName = "Newton"
    }
}