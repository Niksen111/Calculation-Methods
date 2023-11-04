package homeworks.third.first

import homeworks.second.Homework2
import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq
import kotlin.math.abs

class UiH3P1 : Ui{
    private var seq = Seq(-2.0, 2.0)
    private var epsilon = 1.0E-12
    private var degree = Homework3P1.degree
    private var valuesNumber = Homework3P1.valuesNumber
    private var homework = Homework3P1(
        seq = seq,
        epsilon = epsilon,
        func = Homework2.function,
        degree = degree,
        valuesNumber = valuesNumber
    )

    private fun setHomework() {
        homework = Homework3P1(
            seq = seq,
            epsilon = epsilon,
            func = Homework2.function,
            degree = degree,
            valuesNumber = valuesNumber
            )
    }

    private fun printParams() {
        println("Seq = $seq")
        println("Epsilon = $epsilon")
        println("Degree = $degree")
        println("Values number = $valuesNumber")
        println()
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

    private fun setVN(vn: Int) {
        if (vn <= degree) {
            println("[ERROR]: Failed to set up values number")
            println("[ERROR]: Degree must be less then values number")
            println()
            return
        }

        this.valuesNumber = vn
        setHomework()

        println("[WARNING]: Degree must be less then $vn")
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

    private fun printTaskInfo() {
        println("Task № 3.1 Reverse algebraic interpolation")
        println("Option 10")
        println()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                                -print command list")
        println("back                                -quit the current task")
        println("setSeq <left bound> <right bound>   -set up the sequence")
        println("setEps <epsilon>                    -set up epsilon")
        println("setDeg <degree>                     -set up the polynomial degree")
        println("setVN <values number>               -set up the values number")
        println("printTables                         -print tables")
        println("printParams                         -print params")
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
                "printParams" -> {
                    if (input.size != 1) {
                        printErr()
                        continue
                    }

                    printParams()
                }
                "printTables" -> {
                    if (input.size != 1) {
                        printErr()
                        continue
                    }

                    println()
                    println("TABLE 1")
                    println()
                    homework.printTable1()
                    println()
                    println("TABLE 2")
                    println()
                    homework.printTable2()
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
                "setDeg" -> {
                    if (input.size != 2) {
                        printErr()
                        continue
                    }

                    val deg = input[1].toInt()

                    setDegree(deg)
                }
                "setVN" -> {
                    if (input.size != 2) {
                        printErr()
                        continue
                    }

                    val vn = input[1].toInt()

                    setVN(vn)
                }
                "evalAll" -> {
                    if (input.size != 2) {
                        printErr()
                        continue
                    }

                    val point = input[1].toDouble()

                    println("Evaluation by the first method:")
                    val result1 = homework.evalByFirstMethod(point)
                    println(result1)
                    println("The absolute value of the solution discrepancy: ${abs(point - Homework2.function(result1))}")

                    println("Evaluation by the second method:")
                    val result2 = homework.evalBySecondMethod(point)
                    println(result2)
                    println("The absolute value of the solution discrepancy: ${abs(point - Homework2.function(result2))}")
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