package homeworks.fourth.third

import homeworks.fourth.first.Homework4P1
import homeworks.fourth.first.quadrature.QuadratureFormulaManager
import homeworks.fourth.second.CompoundQuadratureFormula
import homeworks.utils.vo.Seq
import kotlin.math.pow
import kotlin.math.abs

class Homework4P3(
    val function: (Double) -> Double = Homework4P1.function,
    val integral: (Double) -> Double = Homework4P1.integral,
    val weight: (Double) -> Double = { x: Double ->
        1.0
    }
) {
    private val manager = QuadratureFormulaManager()

    fun evaluateByFormula(name: String, seq: Seq, ml: Int): Double {
        val formula = manager.getFormulaByName(name)

        return CompoundQuadratureFormula.calculate(formula, function, seq, ml)
    }

    fun getRungeValue(name: String, value: Double, valueML: Double, l: Int): Double {
        val formula = manager.getFormulaByName(name)
        val powered = l.toDouble().pow(formula.rungePown)

        return (powered * valueML - value) / (powered - 1.0)
    }

    fun getAbsError(actualValue: Double, seq: Seq) =
        abs(actualValue - (integral(seq.right) - integral(seq.left)))
}