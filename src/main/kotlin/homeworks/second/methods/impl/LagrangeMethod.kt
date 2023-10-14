package homeworks.second.methods.impl

import homeworks.second.methods.InterpolationMethod
import homeworks.utils.Polynomial
import java.util.SortedMap

class LagrangeMethod : InterpolationMethod {
    override var table: SortedMap<Double, Double> = sortedMapOf()
        private set

    override var degree: Int = 0
        private set

    override fun setUp(table: SortedMap<Double, Double>, degree: Int) {
        this.table = table
        this.degree = degree
    }

    override fun evaluate(point: Double): Double {
        var result = 0.0

        table.map { entry ->
            var lI = entry.value
            val xI = entry.key
            var divide = 1.0
            table.map {
                if (xI != it.key) {
                    val xJ = it.key
                    divide *= xI - xJ
                    lI *= (point - xJ)
                }
            }

            lI /= divide
            result += lI
        }

        return result
    }

    override fun getMethodName() = methodName

    companion object {
        const val methodName = "Lagrange"
    }
}