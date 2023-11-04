package homeworks.fourth.first

import homeworks.fourth.first.quadrature.QuadratureFormulaManager
import homeworks.utils.vo.Seq
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class Homework4P1(
    val function: (Double) -> Double = Homework4P1.function,
    val integral: (Double) -> Double = Homework4P1.integral,
    val seq: Seq = Homework4P1.seq
) {
    private val manager = QuadratureFormulaManager()
    fun getFormulas() = manager.getNames()

    fun calculateByFormula(formulaName: String): Double {
        val formula = manager.getFormulaByName(formulaName)

        return formula.calculate(function, seq)
    }

    fun getAbsError(actualValue: Double) =
        abs(actualValue - (integral(seq.right) - integral(seq.left)))

    companion object {
        val function = { x: Double ->
            3 * x * x + cos(x)
        }
        val integral = { x: Double ->
            x * x * x + sin(x)
        }
        val seq = Seq(0.0, 1.0)
    }
}