package homeworks.fourth.first.quadrature.impl

import homeworks.fourth.first.quadrature.QuadratureFormula
import homeworks.utils.vo.Seq

class ThreeEightsQF : QuadratureFormula {
    override fun calculate(func: (Double) -> Double, seq: Seq): Double {
        val h = seq.size / 3
        return seq.size * (func(seq.left) / 8 + 3 * func(seq.left + h) / 8
            + 3 * func(seq.left + 2 * h) / 8 + func(seq.right) / 8)
    }

    override fun getName() = "ThreeEights"

    override val constCoef = 0.0
    override val rungePown = 0
}