package homeworks.fourth.first.quadrature

import homeworks.utils.vo.Seq
import kotlin.math.abs

interface QuadratureFormula {
    fun calculate(func: (Double) -> Double, seq: Seq): Double

    fun getName(): String
}