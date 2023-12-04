package homeworks.fifth.second

import homeworks.utils.vo.Seq
import kotlin.math.abs
import kotlin.math.sin
import kotlin.math.sqrt

class Homework5P2(val func: (Double) -> Double = function) {
    /* fun getFormulaForN(seq: Seq, N: Int = 8, withInfo: Boolean = false) {
        val formulas = List(N) { ind ->
            GaussQuadratureFormula(seq, ind + 1)
        }
        formulas.forEachIndexed { ind, formula ->
            println("N value: ${ind + 1}")
            for (i in 0 .. ind) {
                println("Coef: ${formula.coef[i]}\tNode: ${formula.roots[i]}")
            }
        }
    } */

    private fun printInfo(formula: GaussQuadratureFormula, N: Int) {
        println("N = $N")
        for (i in 0 until N) {
            println("Coef: ${formula.coef[i]}\tNode: ${formula.roots[i]}")
        }
    }

    fun evaluateCoefsAndNodes(seq: Seq = seqDef, N: Int) {
        val formula = GaussQuadratureFormula(seq, N)
        formula.evaluateParameters()

       printInfo(formula, N)
    }

    fun calculateWithInfo(func: (Double) -> Double = function, seq: Seq = seqDef, N: Int, withInfo: Boolean = false): Double {
        val formula = GaussQuadratureFormula(seq, N)
        val result = formula.calculate(func)

        if (withInfo) {
            printInfo(formula, N)
        }

        return result
    }

    fun getAbsError(calculatedValue: Double, integralValue: Double) =
        abs(calculatedValue - integralValue)

    companion object {
        val function = { x: Double ->
            sqrt(x) * sin(x * x)
        }

        val seqDef = Seq(0.0, 1.0)
    }
}