package main

import homeworks.first.FirstHomework
import homeworks.first.utils.vo.Seq
import kotlin.math.pow

fun main(args: Array<String>) {
    val func: (Double) -> (Double) = { x ->
        1.2 * x.pow(4) + 2 * x.pow(3) - 13 * x.pow(2) - 14.2 * x - 24.1
    }

    val firstDerivative: (Double) -> (Double) = { x ->
        4.8 * x.pow(3) + 6 * x.pow(2) - 26 * x - 14.2
    }

    val secondDerivative: (Double) -> (Double) = { x ->
        14.4 * x.pow(2) + 12 * x - 26
    }

    val seq = Seq(-5.0, 5.0)
    val epsilon = 0.000001
    val firstHomework = FirstHomework(func, seq, epsilon, firstDerivative, secondDerivative)
}