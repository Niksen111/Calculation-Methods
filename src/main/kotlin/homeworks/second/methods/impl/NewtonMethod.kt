package homeworks.second.methods.impl

import homeworks.second.methods.InterpolationMethod
import homeworks.utils.Polynomial
import java.util.SortedMap

class NewtonMethod : InterpolationMethod {
    override var table: SortedMap<Double, Double> = sortedMapOf()
        private set

    override var degree: Int = 0
        private set

    private var polyElements: MutableList<List<Polynomial>> = mutableListOf()

    override fun setUp(table: SortedMap<Double, Double>, degree: Int) {
        this.table = table
        this.degree = degree
        createPolynomial()
    }

    private fun createPolynomial() {
        table.map { entry ->
            val result = mutableListOf(Polynomial(doubleArrayOf(calcDividedDifference(entry.key))))

            table.filter {
                it.key < entry.key
            }.map {
                result.add(Polynomial(doubleArrayOf(-it.key, 1.0)))
            }

            polyElements.add(result)
        }
    }

    override fun evaluate(point: Double): Double {
        var result = 0.0
        polyElements.map { listPolynomial ->
            var temp = 1.0

            listPolynomial.map {
                temp *= it.evaluate(point)
            }

            result += temp
        }

        return result
    }

    private fun calcDividedDifference(x: Double): Double {
        var result = 0.0

        val tablePart = table.filter {
            it.key <= x
        }

        tablePart.map { entry ->
            var product = 1.0
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