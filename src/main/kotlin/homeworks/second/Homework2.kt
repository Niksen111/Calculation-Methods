package homeworks.second

import homeworks.second.methods.InterpolationMethodsManager
import homeworks.utils.vo.Seq
import java.math.BigDecimal
import java.util.SortedMap
import kotlin.math.cos

class Homework2(
    val function: (BigDecimal) -> BigDecimal = Homework2.function,
    val seq: Seq = Homework2.seq,
    val valuesNumber: Int = Homework2.valuesNumber,
    val degree: Int = Homework2.degree
) {
    init {
        require(degree < valuesNumber) { "Values number should be more then degree" }
        require(valuesNumber > 0) { "Values number must be more then 0" }
    }

    val table: SortedMap<BigDecimal, BigDecimal> = setTable()

    val manager = InterpolationMethodsManager()

    fun evaluateByMethod(methodName: String, point: BigDecimal): BigDecimal {
        require(methodName in methodsNames) { "Method name must be in Methods list" }
        val method = manager.getMethod(methodName)
        val step = seq.size() / valuesNumber.toBigDecimal()
        val keys = table.keys.toList()
        var left: BigDecimal = keys[0]
        var right: BigDecimal = keys[degree - 1]
        for (i in 0..<table.size - degree) {
            left = keys[i]
            right = keys[i + degree]
            if ((left + (right - left) / step - point).abs() < step) {
                break
            }
        }

        method.setUp(table.filter { it.key in left..right }.toSortedMap(), degree)
        return method.evaluate(point)
    }

    private fun setTable(): SortedMap<BigDecimal, BigDecimal> {
        val initTable = HashMap<BigDecimal, BigDecimal>()
        val step = seq.size() / valuesNumber.toBigDecimal()
        for (i in 0..<valuesNumber) {
            val x = seq.left + i.toBigDecimal() * step
            initTable[x] = function(x)
        }

        return initTable.toSortedMap()
    }

    fun printTable() {
        table.map { println(it) }
    }

    companion object {
        val methodsNames = listOf("Newton", "Lagrange")
        val function = { x: BigDecimal ->
            cos(x.toDouble()).toBigDecimal() + 2.0.toBigDecimal() * x
        }
        val seq = Seq( 0.5.toBigDecimal(), 1.8.toBigDecimal() )
        const val valuesNumber = 14
        const val degree = 7
    }
}