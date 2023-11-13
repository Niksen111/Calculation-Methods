package homeworks.fourth.third

import homeworks.fourth.first.Homework4P1
import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq
import kotlin.math.pow

class UiH4P3 : Ui {
    private var homework = Homework4P3()
    private var function = Homework4P1.function
    private var integral = Homework4P1.integral
    private var seq = Homework4P1.seq
    private var m = 10
    private var l = 2

    private fun setHomework() {
        homework = Homework4P3(function = function, integral = integral)
    }

    private fun setParams() {
        println("Enter left and right boundaries of the seq:")
        var input = readln().split(" ")
        if (input.size != 2) {
            printErr()
            return
        }

        val left: Double = input[0].toDouble()
        val right: Double = input[1].toDouble()
        setSeq(left, right)

        println("Enter integer m:")
        input = readln().split(" ")
        if (input.size != 1) {
            printErr()
            return
        }

        setM(input[0].toInt())

        println("Enter integer l:")
        input = readln().split(" ")
        if (input.size != 1) {
            printErr()
            return
        }

        setL(input[0].toInt())
    }

    private fun setSeq(left: Double, right: Double) {
        if (left >= right) {
            println("[ERROR]: Failed to set up sequence")
            println("[ERROR]: Left bound must be less then right")
            println()
            return
        }

        this.seq = Seq(left, right)
    }

    private fun setM(m: Int) {
        if (m < 1) {
            println("[ERROR]: Failed to set up m")
            println("[ERROR]: M must be more then 0")
            println()
            return
        }

        this.m = m
    }

    private fun setL(l: Int) {
        if (l < 1) {
            println("[ERROR]: Failed to set up l")
            println("[ERROR]: L must be more then 0")
            println()
            return
        }

        this.l = l
    }

    private fun printTaskInfo() {
        println(
            "Task №4.3 Approximate calculation of the integral using compound quadrature formulas" +
                    " (Depending on multiplier of number of segments and error elimination with Runge method)"
        )
        println(
            """
                Problem parameters: integration limits a, b, weight function ρ(x) and
                function f(x), m – number of division intervals [a, b], l – multiplier of this number.
                We consider the weight p(x) = 1.
                Calculate approximately and print the value of the integral of ρ(x)∙f(x)
                by [a, b] using SCF
                • Left rectangles;
                • Right rectangles;
                • medium rectangles;
                • trapezoid;
                • Simpson
                with parameters m, l. Let us denote these values respectively by J(h) and J(h/l), here h = (b‒a)/m).
                Finally, we reduce error with Runge method.
                
                """.trimIndent()
        )
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
        println("setM                            -set M value")
        println("setL                            -set L value")
        println("tests                           -start tests")
        println()
    }

    private fun printParameters() {
        println("Function: 3 * x^2 + cos(x)")
        println("Sequence: $seq")
        println("M: $m")
        println("L: $l")
        println("H: ${seq.size / (m * l)}")
    }

    private fun printErr() {
        println("[ERROR]: Wrong command")
        println("Print 'help' to see command list")
        println()
    }

    private fun evaluateByAllFormulas() {
        println("Real value of the integral: ${integral(seq.right) - integral(seq.left)}")
        println()
        Homework4P1().getFormulas().forEach { formula ->
            if (formula == "ThreeEights") {
                return@forEach
            }
            val mlValue = homework.evaluateByFormula(formula, seq, m * l)
            val mValue = homework.evaluateByFormula(formula, seq, m)
            val rungeValue = homework.getRungeValue(formula, mValue, mlValue, l)

            println("Quadrature Formula: $formula")
            println("Value J(h): ${mValue}")
            println("Absolute error J(h): ${homework.getAbsError(mValue, seq)}")
            println("Value J(h/l): ${mlValue}")
            println("Absolute error J(h/l): ${homework.getAbsError(mlValue, seq)}")
            println("Runge method value for J(h): ${rungeValue}")
            println("Runge method error for J(h): ${homework.getAbsError(rungeValue, seq)}")
            println()
        }

        println()
    }

    private fun tests() {
        println("Tests: ")
        println()
        m = 10
        seq = Seq(0.0, 10.0)

        println("Seq [0; 10], f(x) = 1, I = x, m = 10, l = $l")
        function = { 1.0 }
        integral = { x -> x }
        setHomework()
        evaluateByAllFormulas()
        println()

        println("Seq [0; 10], f(x) = 2x, I = x^2, m = 10, l = $l")
        function = { x -> 2 * x }
        integral = { x -> x.pow(2) }
        setHomework()
        evaluateByAllFormulas()
        println()

        println("Seq [0; 10], f(x) = 3x^2, I = x^3, m = 10, l = $l")
        function = { x -> 3 * x.pow(2) }
        integral = { x -> x.pow(3) }
        setHomework()
        evaluateByAllFormulas()
        println()

        println("Seq [0; 10], f(x) = 4x^3, I = x^4, m = 10, l = $l")
        function = { x -> 4 * x.pow(3) }
        integral = { x -> x.pow(4) }
        setHomework()
        evaluateByAllFormulas()
        println()

        println("Seq [0; 10], f(x) = 5x^4, I = x^5, m = 10, l = $l")
        function = { x -> 5 * x.pow(4) }
        integral = { x -> x.pow(5) }
        setHomework()
        evaluateByAllFormulas()
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

                    evaluateByAllFormulas()
                }

                "tests" -> {
                    if (input.size > 1) {
                        printErr()
                        continue
                    }

                    tests()
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

                "setM" -> {
                    if (input.size != 2) {
                        printErr()
                        continue
                    }

                    val m = input[1].toInt()

                    setM(m)
                }

                "setL" -> {
                    if (input.size != 2) {
                        printErr()
                        continue
                    }

                    val l = input[1].toInt()

                    setL(l)
                }

                "printParams" -> {
                    if (input.size > 1) {
                        printErr()
                        continue
                    }

                    printParameters()
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