package homeworks.fifth.first

import homeworks.fourth.second.CompoundQuadratureFormula
import homeworks.utils.vo.Seq
import kotlin.math.sin
import kotlin.math.sqrt

class Homework5P1 {


    companion object {
        val f: (Double) -> Double = { x: Double -> sin(x) }
        val p: (Double) -> Double = { x: Double -> sqrt(1 - x) }
        val seq: Seq = Seq(0, 1)
        private const val M = 100000
        val pseudoIntegral = { _: Seq ->
            0.2502016951430149 }
        const val RESULT = 0.2502016951430149
    }
}