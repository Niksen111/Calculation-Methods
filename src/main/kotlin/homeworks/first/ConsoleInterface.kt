package homeworks.first

import homeworks.first.utils.vo.Seq
import homeworks.first.utils.SolutionInfoProcessor

/**
 * @author nksenchik
 * @since 1.0.0
 */
class ConsoleInterface {
    private var seq = Seq(-5.0, 5.0)
    private var number = 100000

    private fun printTaskInfo() {
        println("Task №1 NUMERICAL METHODS FOR SOLVING NONLINEAR EQUATIONS")
        println("Function to be used:")
        println("1.2 * x^4 + 2 * x^3 - 13 * x^2 - 14.2 * x - 24.1")
        println("On the segment: [${seq.left};${seq.right}]")
        println("Number of sub-segments to split: $number")
        println()
    }

    private fun setSeq(left: Double, right: Double) {
        if (left >= right) {
            println("[ERROR]: Failed to set up sequence")
            println("[ERROR]: Left bound must be less then right")
            println()
            return
        }

        seq = Seq(left, right)
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

    private fun printCommandList() {
        println("Commands list:")
        println("help                                  -print command list")
        println("quit                                  -quit the app")
        println("setSeq <left bound> <right bound>     -set up the sequence")
        println("setNum <number>                -set up subsequences number")
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

    fun interactWithUser() {
        val homework = FirstHomework()
        val processor = SolutionInfoProcessor()

        printTaskInfo()
        printCommandList()

        while (true) {
            println("Enter a command:")
            val command = readln()
            val separatedCommand = command.split(" ")

            if (separatedCommand.isEmpty() || separatedCommand.size > 3) {
                printErr()
                continue
            }

            when (separatedCommand.first()) {
                "help" -> {
                    if (separatedCommand.size > 1) {
                        printErr()
                        continue
                    }

                    printCommandList()
                }
                "quit" -> {
                    if (separatedCommand.size > 1) {
                        printErr()
                        continue
                    }

                    break
                }
                "setSeq" -> {
                    if (separatedCommand.size != 3) {
                        printErr()
                        continue
                    }

                    val left: Double
                    val right: Double
                    try {
                        left = separatedCommand[1].toDouble()
                        right = separatedCommand[2].toDouble()
                    } catch (e: Exception) {
                        printErr()
                        continue
                    }

                    setSeq(left, right)
                }
                "setNum" -> {
                    if (separatedCommand.size != 2) {
                        printErr()
                        continue
                    }

                    val number: Int
                    try {
                        number = separatedCommand[1].toInt()
                    } catch (e: Exception) {
                        printErr()
                        continue
                    }

                    setSubsequenceNumber(number)
                }
                "solve" -> {
                    if (separatedCommand.size != 2 || separatedCommand[1] !in methodsNames) {
                        printErr()
                        continue
                    }

                    try {
                        val sequences = homework.separateSolutions(number)
                        println("Method: ${separatedCommand[1]}")
                        println("Sequences number: ${sequences.size}")
                        sequences.forEach {
                            println(it)
                        }
                        println()
                        sequences.forEach {
                            val solutionInfo = homework.findSolutionByMethod(separatedCommand[1], it)
                            processor.processInfo(solutionInfo)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                "solveAll" -> {
                    if (separatedCommand.size > 1) {
                        printErr()
                        continue
                    }

                    try {
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
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                else -> {
                    printErr()
                }
            }
        }
    }

    companion object {
        val methodsNames = listOf("Bisection", "Newton", "Modified_Newton", "Secant")
    }
}