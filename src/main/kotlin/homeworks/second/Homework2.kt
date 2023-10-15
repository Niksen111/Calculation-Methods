package homeworks.second

import homeworks.second.methods.InterpolationMethodsManager
import homeworks.utils.vo.Seq
import java.util.SortedMap
import kotlin.math.abs
import kotlin.math.cos

class Homework2(
    val function: (Double) -> Double = Homework2.function,
    val seq: Seq = Homework2.seq,
    val valuesNumber: Int = Homework2.valuesNumber,
    val degree: Int = Homework2.degree,
    val table: SortedMap<Double, Double> = Homework2.createTable(function, seq, valuesNumber)
) {
    init {
        require(degree < valuesNumber) { "Values number should be more then degree" }
        require(valuesNumber > 0) { "Values number must be more then 0" }
    }

    val manager = InterpolationMethodsManager()

    fun evaluateByMethod(methodName: String, point: Double): Double {
        require(methodName in methodsNames) { "Method name must be in Methods list" }
        val method = manager.getMethod(methodName)
        val step = seq.size() / valuesNumber
        val keys = table.keys.toList()
        var left: Double = keys[0]
        var right: Double = keys[degree - 1]
        for (i in 0..<table.size - degree) {
            left = keys[i]
            right = keys[i + degree]
            if (abs(left + (right - left) / step - point) < step) {
                break
            }
        }

        method.setUp(table.filter { it.key in left..right }.toSortedMap(), degree)
        return method.evaluate(point)
    }

    fun printTable() {
        table.map { println(it) }
    }

    companion object {
        val methodsNames = listOf("Newton", "Lagrange")
        val function = { x: Double ->
            cos(x) + 2 * x
        }
        val seq = Seq( 0.5, 1.8 )
        const val valuesNumber = 14
        const val degree = 7

        fun createTable(function: (Double) -> Double, seq: Seq, valuesNumber: Int): SortedMap<Double, Double> {
            val initTable = HashMap<Double, Double>()
            val step = seq.size() / valuesNumber
            for (i in 0..<valuesNumber) {
                val x = seq.left + i * step
                initTable[x] = function(x)
            }

            return initTable.toSortedMap()
        }
    }
}