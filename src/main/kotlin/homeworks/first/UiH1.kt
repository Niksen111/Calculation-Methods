package homeworks.first

import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq
import homeworks.first.utils.SolutionInfoProcessor
import homeworks.first.Homework1.Companion.defaultData

/**
 * @author nksenchik
 * @since 1.0.0
 */
class UiH1 : Ui {
    private var seq = Seq(-5.0, 5.0)
    private var number = 1000
    private var epsilon = 1.0E-6
    private var homework = Homework1(
        func = defaultData.func,
        seq = seq,
        epsilon = epsilon,
        firstDerivative = defaultData.firstDerivative,
        secondDerivative = defaultData.secondDerivative
    )
    private val processor = SolutionInfoProcessor()

    private fun printTaskInfo() {
        println("Task №1 NUMERICAL METHODS FOR SOLVING NONLINEAR EQUATIONS")
        println("Function to be used:")
        println("1.2 * x^4 + 2 * x^3 - 13 * x^2 - 14.2 * x - 24.1")
        println("On the segment: [${seq.left};${seq.right}]")
        println("Number of sub-segments to split: $number")
        println("Epsilon: $epsilon")
        println()
    }

    private fun setHomework() {
        this.homework = Homework1(
            func = defaultData.func,
            seq = seq,
            epsilon = epsilon,
            firstDerivative = defaultData.firstDerivative,
            secondDerivative = defaultData.secondDerivative
        )
    }

    private fun setSeq(left: Double, right: Double) {
        if (left >= right) {
            println("[ERROR]: Failed to set up sequence")
            println("[ERROR]: Left bound must be less then right")
            println()
            return
        }

        this.seq = Seq(left, right)
        setHomework()
    }

    private fun setSubsequenceNumber(number: Int) {
        if (number < 2) {
            println("[ERROR]: Failed to set up subsequence number")
            println("[ERROR]: Subsequence number must be more then 1")
            println()
            return
        }

        this.number = number
    }


    private fun setEpsilon(eps: Double) {
        if (eps <= 0.0) {
            println("[ERROR]: Failed to set up epsilon")
            println("[ERROR]: Epsilon must be more then 0")
            println()
            return
        }

        this.epsilon = eps
        setHomework()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                                  -print command list")
        println("back                                  -quit the current task")
        println("setSeq <left bound> <right bound>     -set up the sequence")
        println("setNum <number>                       -set up subsequences number")
        println("setEps <epsilon>                      -set up epsilon")
        println("solve <method name>                   -find solution by the method")
        println("solveAll                              -find solution by all the methods")
        println("Methods names:")
        println("   Bisection")
        println("   Newton")
        println("   Modified_Newton")
        println("   Secant")
        println()
    }

    private fun printErr() {
        println("[ERROR]: Wrong command")
        println()
    }

    override fun start() {
        printTaskInfo()
        printCommandList()

        while (true) {
            try {
                println("Enter a command:")
                val input = readln().split(" ")

                if (input.isEmpty() || input.size > 3) {
                    printErr()
                    continue
                }

                when (input.first()) {
                    "help" -> {
                        if (input.size > 1) {
                            printErr()
                            continue
                        }

                        printCommandList()
                    }

                    "back" -> {
                        if (input.size > 1) {
                            printErr()
                            continue
                        }

                        break
                    }

                    "setSeq" -> {
                        if (input.size != 3) {
                            printErr()
                            continue
                        }

                        val left: Double
                        val right: Double
                        try {
                            left = input[1].toDouble()
                            right = input[2].toDouble()
                        } catch (e: Exception) {
                            printErr()
                            continue
                        }

                        setSeq(left, right)
                    }

                    "setNum" -> {
                        if (input.size != 2) {
                            printErr()
                            continue
                        }

                        val number: Int
                        try {
                            number = input[1].toInt()
                        } catch (e: Exception) {
                            printErr()
                            continue
                        }

                        setSubsequenceNumber(number)
                    }

                    "setEps" -> {
                        if (input.size != 2) {
                            printErr()
                            continue
                        }

                        val eps: Double
                        try {
                            eps = input[1].toDouble()
                        } catch (e: Exception) {
                            printErr()
                            continue
                        }

                        setEpsilon(eps)
                    }

                    "solve" -> {
                        if (input.size != 2 || input[1] !in methodsNames) {
                            printErr()
                            continue
                        }

                        try {
                            val sequences = homework.separateSolutions(number)
                            println("Method: ${input[1]}")
                            println("Sequences number: ${sequences.size}")
                            sequences.forEach {
                                println(it)
                            }
                            println()
                            sequences.forEach {
                                val solutionInfo = homework.findSolutionByMethod(input[1], it)
                                processor.processInfo(solutionInfo)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    "solveAll" -> {
                        if (input.size > 1) {
                            printErr()
                            continue
                        }
                        val sequences = homework.separateSolutions(number)
                        println("Sequences number: ${sequences.size}")
                        println()
                        sequences.forEach {
                            println(it)
                            println()
                            methodsNames.forEach { methodName ->
                                println("### Method: $methodName")
                                val solutionInfo = homework.findSolutionByMethod(methodName, it)
                                processor.processInfo(solutionInfo)
                            }
                        }
                    }
                    else -> {
                        printErr()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        val methodsNames = listOf("Bisection", "Newton", "Modified_Newton", "Secant")
    }
}