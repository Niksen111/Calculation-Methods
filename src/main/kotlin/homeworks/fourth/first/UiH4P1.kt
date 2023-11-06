package homeworks.fourth.first

import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq
import kotlin.math.pow

class UiH4P1 : Ui {
    private var homework = Homework4P1()
    private val function = Homework4P1.function
    private val integral = Homework4P1.integral
    private var seq = Homework4P1.seq

    private fun setHomework() {
        homework = Homework4P1(function, integral, seq)
    }

    private fun setParams() {
        println("Enter left and right boundaries of the seq:")
        val input = readln().split(" ")
        if (input.size != 2) {
            printErr()
            return
        }

        val left: Double = input[0].toDouble()
        val right: Double = input[1].toDouble()
        setSeq(left, right)
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
        println("Task №4.1 approximate calculation of the integral using quadrature formulas")
        println("(We consider the weight p(x) = 1.)")
        println("Let us calculate approximately the value of the integral of f(x) over [a, b] using:")
        println("• QF of the left rectangle;")
        println("• QF of the right rectangle;")
        println("• QF of the middle rectangle;")
        println("• QF trapezoid;")
        println("• Simpson's QF (or parabolas);")
        println("• QF 3/8.")
        printParameters()
        println()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                            -print command list")
        println("back                            -back ")
        println("evalAll                         -evaluate by all formulas")
        println("setParams                       -set all the parameters")
        println("printParams                     -print all the parameters")
        println("tests                           -start tests")
        println()
    }

    private fun printParameters() {
        println("Function: 3 * x^2 + cos(x)")
        println("Sequence: $seq")
    }

    private fun printErr() {
        println("[ERROR]: Wrong command")
        println()
    }

    private fun tests() {
        println("Tests: ")
        println()
        val seq = Seq(0.0, 10.0)
        println("Seq [0; 10], f(x) = 1, I = x")
        var homework = Homework4P1({ 1.0 }, { x -> x }, seq)
        evaluateByAllFormulas(homework)
        println()
        println("Seq [0; 10], f(x) = 2x, I = x^2")
        homework = Homework4P1({ x -> 2 * x }, { x -> x.pow(2) }, seq)
        evaluateByAllFormulas(homework)
        println()
        println("Seq [0; 10], f(x) = 3x^2, I = x^3")
        homework = Homework4P1({ x -> 3 * x.pow(2) }, { x -> x.pow(3) }, seq)
        evaluateByAllFormulas(homework)
        println()
        println("Seq [0; 10], f(x) = 4x^3, I = x^4")
        homework = Homework4P1({ x -> 4 * x.pow(3) }, { x -> x.pow(4) }, seq)
        evaluateByAllFormulas(homework)
        println()
        println("Seq [0; 10], f(x) = 5x^4, I = x^5")
        homework = Homework4P1({ x -> 5 * x.pow(4) }, { x -> x.pow(5) }, seq)
        evaluateByAllFormulas(homework)
        println()
    }

    private fun evaluateByAllFormulas(homework: Homework4P1) {
        println("Real value of the integral: ${homework.integral(homework.seq.right) - homework.integral(homework.seq.left)}")
        println()
        homework.getFormulas().forEach { formula ->
            println("Quadrature Formula: $formula")
            println("Value: ${homework.calculateByFormula(formula)}")
            println("Absolute error: ${homework.getAbsError(homework.calculateByFormula(formula))}")
            println()
        }

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
                "evalAll" -> {
                    if (input.size > 1) {
                        printErr()
                        continue
                    }

                    evaluateByAllFormulas(homework)
                }
                "setParams" -> {
                    if (input.size > 1) {
                        printErr()
                        continue
                    }

                    setParams()
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
                "printParams" -> {
                    if (input.size > 1) {
                        printErr()
                        continue
                    }

                    printParameters()
                }
                "tests" -> {
                    if (input.size > 1) {
                        printErr()
                        continue
                    }

                    tests()
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