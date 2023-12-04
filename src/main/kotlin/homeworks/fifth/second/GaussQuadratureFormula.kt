package homeworks.fifth.second

import homeworks.first.Homework1
import homeworks.first.utils.vo.HomeworkData
import homeworks.utils.vo.Seq
import kotlin.math.pow

class GaussQuadratureFormula(
    val seq: Seq,
    val N: Int
) {
    val polynomials = MutableList(N + 1) {
        { _: Double -> 1.0 }
    }

    val roots = MutableList(N) {
        0.0
    }

    val coef = MutableList(N) {
        0.0
    }

    init {
        for (i in 0 ..N) {
            when (i) {
                0 -> {
                    polynomials[i] = { _: Double -> 1.0 }
                }

                1 -> {
                    polynomials[i] = { x: Double -> x }
                }

                else -> {
                    polynomials[i] = { x: Double ->
                        val cf1 = ((2.0 * i.toDouble() - 1.0) / i.toDouble())
                        val cf2 = - ((i.toDouble() - 1.0) / i.toDouble())
                        val res = cf1 * polynomials[i - 1](x) * x + cf2 * polynomials[i - 2](x)
                        res
                    }
                }
            }

            // println("$i ${polynoms[i](-1.0)} ${polynoms[i](1.0)}")
        }

        val rootsData = HomeworkData(
            func = polynomials[N],
            seq = Seq(-1.0, 1.0),
            epsilon = 10.0.pow(-12),
            firstDerivative = { 1.0 },
            secondDerivative = { 1.0 }
        )
        val rootsDetection = Homework1(rootsData)
        rootsDetection.separateSolutions(200).mapIndexed { ind, rootSeq ->
            roots[ind] = rootsDetection.findSolutionByMethod("Secant", rootSeq).result
        }

        for (i in 0 until N) {
            val calculatedPolynomial = polynomials[N - 1](roots[i])
            coef[i] = 2.0 * (1.0 - roots[i] * roots[i]) / (N.toDouble() * N.toDouble() * calculatedPolynomial * calculatedPolynomial)
            // coef[N - 1 - i] = coef[i]
        }

        // println("========================== INIT ENDED")
    }

    fun evaluateParameters() {
        val (a, b) = seq
        for (i in 0 until N) {
            roots[i] = (seq.size / 2.0) * roots[i] + (b + a) / 2.0
            coef[i] *= seq.size / 2.0
        }
    }

    fun calculate(func: (Double) -> Double): Double {
        // println("========================== ROOTS: $roots")
        // println("========================== SUM OF COEF: ${coef.sum()}")
        // println("========================== COEF: $coef")
        evaluateParameters()
        // println("========================== ROOTS: $roots")
        // println("========================== SUM OF COEF: ${coef.sum()}")
        var result = 0.0
        for (i in 0 until N) {
            result += coef[i] * func(roots[i])
        }
        return result
    }
}