package homeworks.fourth.first.quadrature.impl

import homeworks.fourth.first.quadrature.QuadratureFormula
import homeworks.utils.vo.Seq

class SimpsonQF : QuadratureFormula {
    override fun calculate(func: (Double) -> Double, seq: Seq): Double {
        return seq.size / 6 * (func(seq.left) + 4 * func(seq.middle) + func(seq.right))
    }

    override fun getName() = "Simpson"
}