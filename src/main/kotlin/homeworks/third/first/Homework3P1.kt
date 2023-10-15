package homeworks.third.first

import homeworks.first.Homework1
import homeworks.second.Homework2
import homeworks.utils.vo.Seq

class Homework3P1 {
    private var homework2 = Homework2()

    fun evalByFirstMethod(point: Double): Double {
        val seq = Seq(-3.0, 3.0)
        val valuesNumber = 30
        homework2 = Homework2(
            table = Homework2.createTable(Homework2.function, seq, valuesNumber)
                .map { (it.value to it.key) }.toMap().toSortedMap(),
            seq = seq,
            valuesNumber = valuesNumber,
            degree = 29
        )

        return homework2.evaluateByMethod("Lagrange", point)
    }

    fun evalBySecondMethod(point: Double): Double {
        val homework1 = Homework1(
            func = { x: Double -> homework2.function(x) - point },
            seq = Seq(-2.0, 2.0),
            epsilon = 1.0e-9,
            firstDerivative = { x -> x },
            secondDerivative = { x -> x }
        )

        return homework1.findSolutionByMethod("Secant", Seq(-2.0, 2.0)).result
    }
}