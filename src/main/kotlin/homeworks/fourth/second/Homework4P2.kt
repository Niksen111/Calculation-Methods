package homeworks.fourth.second

import homeworks.fourth.first.Homework4P1
import homeworks.fourth.first.quadrature.QuadratureFormulaManager
import homeworks.utils.vo.Seq
import kotlin.math.abs

class Homework4P2(
    val function: (Double) -> Double = Homework4P1.function,
    val integral: (Double) -> Double = Homework4P1.integral
) {
    private val manager = QuadratureFormulaManager()

    fun evaluateByFormula(name: String, seq: Seq, m: Int): Double {
        val formula = manager.getFormulaByName(name)

        return CompoundQuadratureFormula.calculate(formula, function, seq, m)
    }

    fun getAbsError(actualValue: Double, seq: Seq) =
        abs(actualValue - (integral(seq.right) - integral(seq.left)))
}