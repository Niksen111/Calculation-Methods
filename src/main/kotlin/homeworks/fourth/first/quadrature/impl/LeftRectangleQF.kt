package homeworks.fourth.first.quadrature.impl

import homeworks.fourth.first.quadrature.QuadratureFormula
import homeworks.utils.vo.Seq

class LeftRectangleQF : QuadratureFormula {
    override fun calculate(func: (Double) -> Double, seq: Seq): Double {
        return seq.size * func(seq.left)
    }

    override fun getName() = "LeftRectangle"
}