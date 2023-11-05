package homeworks.fourth.second

import homeworks.fourth.first.quadrature.QuadratureFormula
import homeworks.utils.vo.Seq

class CompoundQuadratureFormula {
    companion object {
        fun calculate(
            quadratureFormula: QuadratureFormula,
            func: (Double) -> Double,
            seq: Seq,
            m: Int
        ): Double {
            val h = seq.size / m
            var result = 0.0

            for (i in 1..m) {
                result += quadratureFormula.calculate(func, Seq(seq.left + h * (i - 1), seq.left + h * i))
            }

            return result
        }
    }
}