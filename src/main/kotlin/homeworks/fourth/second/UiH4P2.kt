package homeworks.fourth.second

import homeworks.fourth.first.Homework4P1
import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq
import kotlin.math.pow

class UiH4P2 : Ui {
    private var homework = Homework4P2()
    private var function = Homework4P1.function
    private var integral = Homework4P1.integral
    private var seq = Homework4P1.seq
    private var m = 10
    
    private fun setHomework() {
        homework = Homework4P2(function, integral)
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
    }
    
    private fun setM(m: Int) {
        this.m = m
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

    private fun printTaskInfo() {
        println("Task №4.2 Approximate calculation of the integral using compound quadrature formulas")
        println(
            """
                Problem parameters: integration limits a, b, weight function ρ(x) and
                function f(x), m – number of division intervals [a, b].
                We consider the weight p(x) = 1.
                Calculate approximately and print the value of the integral of ρ(x)∙f(x)
                by [a, b] using SCF
                • Left rectangles;
                • Right rectangles;
                • medium rectangles;
                • trapezoid;
                • Simpson
                with parameter m. Let us denote these values by J(h), here h = (b‒a)/m).
                
                """.trimIndent()
        )
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
        println("M: $m")
    }

    private fun printErr() {
        println("[ERROR]: Wrong command")
        println()
    }

    private fun evaluateByAllFormulas() {
        println("Real value of the integral: ${integral(seq.right) - integral(seq.left)}")
        println()
        Homework4P1().getFormulas().forEach { formula ->
            println("Quadrature Formula: $formula")
            println("Value: ${homework.evaluateByFormula(formula, seq, m)}")
            println("Absolute error: ${homework.getAbsError(homework.evaluateByFormula(formula, seq, m), seq)}")
            println()
        }

        println()
    }

    private fun tests() {
        println("Tests: ")
        println()
        m = 10
        seq = Seq(0.0, 10.0)
        
        println("Seq [0; 10], f(x) = 1, I = x, m = 10")
        function = { 1.0 }
        integral = { x -> x }
        setHomework()
        evaluateByAllFormulas()
        println()
        
        println("Seq [0; 10], f(x) = 2x, I = x^2, m = 10")
        function = { x -> 2 * x }
        integral = { x -> x.pow(2) }
        setHomework()
        evaluateByAllFormulas()
        println()
        
        println("Seq [0; 10], f(x) = 3x^2, I = x^3, m = 10")
        function = { x -> 3 * x.pow(2) }
        integral = { x -> x.pow(3) }
        setHomework()
        evaluateByAllFormulas()
        println()
        
        println("Seq [0; 10], f(x) = 4x^3, I = x^4, m = 10")
        function = { x -> 4 * x.pow(3) }
        integral = { x -> x.pow(4) }
        setHomework()
        evaluateByAllFormulas()
        println()
        
        println("Seq [0; 10], f(x) = 5x^4, I = x^5, m = 10")
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