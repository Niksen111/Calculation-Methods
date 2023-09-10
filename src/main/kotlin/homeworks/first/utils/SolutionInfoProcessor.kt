package homeworks.first.utils

import homeworks.first.utils.vo.SolutionInfo

class SolutionInfoProcessor {
    fun processInfo(info: SolutionInfo) {
        println("Result: ${info.result}")
        if (info.methodName == "Bisection") {
            println("Length of the resulting segment: ${info.seqLength}")
        }
        println("The absolute value of the solution discrepancy: ${info.absoluteDiscrepancy}")
        println("Number of steps to obtain the required accuracy: ${info.stepsNumber}")
        println()
    }
}