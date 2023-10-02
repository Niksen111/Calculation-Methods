package homeworks.second

import homeworks.second.methods.InterpolationMethodsManager
import homeworks.utils.vo.Seq
import kotlin.math.cos

class SecondHomework(
    val function: (Double) -> Double = SecondHomework.function,
    val seq: Seq = SecondHomework.seq,
    val valuesNumber: Int = SecondHomework.valuesNumber,
    val degree: Int = SecondHomework.degree
) {
    init {
        require(degree >= valuesNumber) { "Values number should not be more then degree" }
        require(valuesNumber > 0) { "Values number must be more then 0" }
    }

    val table: Map<Double, Double> = setTable()

    val manager = InterpolationMethodsManager()

    fun evaluateByMethod(methodName: String, point: Double): Double {
        require(methodName in methodsNames) { "Method name must be in Methods list" }
        val method = manager.getMethod(methodName)
        return method.evaluate(point)
    }

    private fun setTable(): HashMap<Double, Double> {
        val initTable = HashMap<Double, Double>()
        val step = seq.size() / valuesNumber
        for (i in 0..<valuesNumber) {
            val x = seq.left + i * step
            initTable[x] = function(x)
        }

        return initTable
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
        const val valuesNumber = 7
        const val degree = 14
    }
}