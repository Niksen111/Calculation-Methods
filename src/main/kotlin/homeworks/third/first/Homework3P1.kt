package homeworks.third.first

import homeworks.first.Homework1
import homeworks.second.Homework2
import homeworks.utils.vo.Seq

class Homework3P1(
    val func: (Double) -> Double = Homework2.function,
    val seq: Seq = Homework3P1.seq,
    val epsilon: Double = Homework3P1.epsilon,
    val valuesNumber: Int = 30,
    val degree: Int = 29
) {
    init {
        require(degree < valuesNumber) { "Values number should be more then degree" }
        require(valuesNumber > 0) { "Values number must be more then 0" }
    }

    private val h21 = Homework2(
        function = func,
        table = Homework2.createTable(Homework2.function, seq, valuesNumber)
            .map { (it.value to it.key) }.toMap().toSortedMap(),
        seq = seq,
        valuesNumber = valuesNumber,
        degree = degree
    )

    private val h22 = Homework2(
        function = func,
        table = Homework2.createTable(Homework2.function, seq, valuesNumber),
        seq = seq,
        valuesNumber = valuesNumber,
        degree = degree
    )

    fun printTable1() {
        h21.printTable()
    }

    fun printTable2() {
        h22.printTable()
    }

    fun evalByFirstMethod(point: Double): Double {
        return h21.evaluateByMethod("Lagrange", point)
    }

    fun evalBySecondMethod(point: Double): Double {
        val homework1 = Homework1(
            func = { x: Double -> h22.evaluateByMethod("Lagrange", x) - point },
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
        const val degree = 29
        const val valuesNumber = 30
    }
}