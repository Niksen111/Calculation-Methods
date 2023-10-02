package homeworks.second

import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq

class UiH2 : Ui {
    private var valuesNumber = SecondHomework.valuesNumber
    private var seq = SecondHomework.seq
    private var degree = SecondHomework.degree
    private var homework: SecondHomework = SecondHomework()

    private fun setHomework() {
        this.homework = SecondHomework(
            SecondHomework.function,
            seq,
            valuesNumber,
            degree
        )
    }

    private fun setDegree(deg: Int) {
        if (degree <= 0) {
            println("[ERROR]: Failed to set up degree")
            println("[ERROR]: Degree must be more then 0")
            println()
            return
        }

        this.degree = deg
        setHomework()
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

    private fun printTaskInfo() {
        println("Task №2 ALGEBRAIC INTERPOLATION PROBLEM")
        println("Option 10")
        println("Function to be used:")
        println("cos(x) + 2 * x")
        println("On the segment: [${seq.left}; ${seq.right}]")
        println("Number of values in the table: $valuesNumber")
        println("Polynomial degree: $degree")
        println()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                                  -print command list")
        println("back                                  -quit the current task")
        println("setSeq <left bound> <right bound>     -set up the sequence")
        println("setDeg <degree>                       -set up the polynomial degree")
        println("evalAll <point>                       -evaluate the value by all the methods at a given point")
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
                    "setDeg" -> {
                        if (input.size != 2) {
                            printErr()
                            continue
                        }

                        val deg = input[1].toInt()

                        setDegree(deg)
                    }
                    "evalAll" -> {
                        if (input.size != 2) {
                            printErr()
                            continue
                        }

                        val point = input[1].toDouble()

                        SecondHomework.methodsNames.map {
                            val result = homework.evaluateByMethod(it, point)
                            println(result)
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
}