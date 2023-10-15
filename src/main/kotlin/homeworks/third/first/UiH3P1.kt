package homeworks.third.first

import homeworks.second.Homework2
import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq

class UiH3P1 : Ui{
    private var seq = Seq(-5.0, 5.0)
    private var epsilon = 1.0E-9
    private var homework = Homework3P1(
        seq = seq,
        epsilon = epsilon,
        func = Homework2.function
    )

    private fun setHomework() {
        homework = Homework3P1(
            seq = seq,
            epsilon = epsilon,
            func = Homework2.function
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

    private fun printTaskInfo() {
        println("Task № 3.1")
        println("Option 10")
        println()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                                -print command list")
        println("back                                -quit the current task")
        println("setSeq <left bound> <right bound>   -set up the sequence")
        println("setEps <epsilon>                    -set up epsilon")
        println("evalAll <point>                     -evaluate by all methods")
        println()
    }

    private fun printErr() {
        println("[ERROR]: Wrong command")
        println()
    }

    override fun start() {
        printTaskInfo()
        printCommandList()
        while (true) try {
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

                    val left: Double = input[1].toDouble()
                    val right: Double = input[2].toDouble()
                    setSeq(left, right)
                }
                "setEps" -> {
                    if (input.size != 2) {
                        printErr()
                        continue
                    }

                    val eps: Double = input[1].toDouble()

                    setEpsilon(eps)
                }
                "evalAll" -> {
                    if (input.size != 2) {
                        printErr()
                        continue
                    }

                    val point = input[1].toDouble()

                    println(homework.evalByFirstMethod(point))
                    println(homework.evalBySecondMethod(point))
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