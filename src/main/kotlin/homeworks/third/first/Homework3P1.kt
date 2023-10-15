package homeworks.third.first

import homeworks.first.Homework1
import homeworks.second.Homework2
import homeworks.utils.vo.Seq

class Homework3P1(
    val func: (Double) -> Double = Homework2.function,
    val seq: Seq = Homework3P1.seq,
    val epsilon: Double = Homework3P1.epsilon,
) {

    fun evalByFirstMethod(point: Double): Double {
        val valuesNumber = 30
        val homework2 = Homework2(
            function = func,
            table = Homework2.createTable(Homework2.function, seq, valuesNumber)
                .map { (it.value to it.key) }.toMap().toSortedMap(),
            seq = seq,
            valuesNumber = valuesNumber,
            degree = valuesNumber - 1
        )

        return homework2.evaluateByMethod("Lagrange", point)
    }

    fun evalBySecondMethod(point: Double): Double {
        val homework2 = Homework2(
            function = func,
            table = Homework2.createTable(Homework2.function, seq, 30)
                .map { (it.value to it.key) }.toMap().toSortedMap(),
            seq = seq,
            valuesNumber = 30,
            degree = 29
        )
        val homework1 = Homework1(
            func = { x: Double -> homework2.function(x) - point },
            seq = seq,
            epsilon = epsilon,
            firstDerivative = { x -> x },
            secondDerivative = { x -> x }
        )

        return homework1.findSolutionByMethod("Secant", seq).result
    }

    companion object {
        val seq = Seq(-3.0, 3.0)
        const val epsilon = 1.0e-9
    }
}