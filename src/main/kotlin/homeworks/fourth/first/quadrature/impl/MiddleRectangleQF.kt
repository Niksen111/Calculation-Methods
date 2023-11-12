package homeworks.fourth.first.quadrature.impl

import homeworks.fourth.first.quadrature.QuadratureFormula
import homeworks.utils.vo.Seq

class MiddleRectangleQF : QuadratureFormula {
    override fun calculate(func: (Double) -> Double, seq: Seq): Double {
        return seq.size * func(seq.middle)
    }

    override fun getName() = "MiddleRectangle"

    override val constCoef = 1.0 / 24.0
    override val rungePown = 2
}