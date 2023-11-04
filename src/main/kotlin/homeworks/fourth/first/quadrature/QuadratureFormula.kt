package homeworks.fourth.first.quadrature

import homeworks.utils.vo.Seq
import kotlin.math.abs

interface QuadratureFormula {
    fun calculate(func: (Double) -> Double, seq: Seq): Double

    fun absError(actualValue: Double, integral: (Double) -> Double, seq: Seq) =
        abs(actualValue - (integral(seq.right) - integral(seq.left)))

    fun getName(): String
}