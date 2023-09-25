package homeworks.second

import homeworks.utils.vo.Seq
import java.util.HashMap
import kotlin.math.cos

class SecondHomework(
    val function: (Double) -> Double = SecondHomework.function,
    val seq: Seq = SecondHomework.seq,
    val valuesNumber: Int = SecondHomework.valuesNumber,
    val degree: Int = SecondHomework.degree
) {
    val table: Map<Double, Double> = setTable()

    private fun setTable(): HashMap<Double, Double> {
        val initTable = HashMap<Double, Double>()
        val step = seq.size() / valuesNumber
        for (i in 0..<valuesNumber) {
            val x = seq.left + i * step
            initTable[x] = function(x)
        }

        return initTable
    }



    companion object {
        val function = { x: Double ->
            cos(x) + 2 * x
        }
        val seq = Seq( -5.0, 5.0 )
        const val valuesNumber = 10
        const val degree = 10
    }
}