package homeworks.fourth.first.quadrature

import homeworks.utils.vo.Seq

interface QuadratureFormula {
    fun calculate(func: (Double) -> Double, seq: Seq): Double

    fun getName(): String

    val constCoef: Double
    val rungePown: Int
}