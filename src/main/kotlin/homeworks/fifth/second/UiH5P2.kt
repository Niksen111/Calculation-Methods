package homeworks.fifth.second

import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq
import kotlin.math.pow

class UiH5P2: Ui {
    private var homework = Homework5P2()
    private var function = Homework5P2.function
    private var integral = { _: Double -> 1.0 }
    private var seq = Homework5P2.seqDef
    private var integralValue = 0.0
    private var Ns = List(3) { ind -> ind + 6 }

    private fun setHomework() {
        homework = Homework5P2(function)
    }

    private fun setDefault() {
        homework = Homework5P2()
        function = Homework5P2.function
        integral = { _: Double -> 1.0 }
        seq = Homework5P2.seqDef
        Ns = List(3) { ind -> ind + 6 }
    }

    private fun updateIntegralValue(isDefault: Boolean = true) {
        integralValue = if (isDefault) {
            setSeq(Homework5P2.seqDef.left, Homework5P2.seqDef.right)
            0.2642040406133
        } else {
            integral(seq.right) - integral(seq.left)
        }
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

        println("Enter integer N1, N2, N3:")
        input = readln().split(" ")
        if (input.size != 3) {
            printErr()
            return
        }

        setNs(input.map { stringN -> stringN.toInt() })
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

    private fun setNs(newNs: List<Int>) {
        newNs.forEachIndexed { ind, N ->
            if (N < 1) {
                println("[ERROR]: Failed to set up N${ind + 1}")
                println("[ERROR]: N${ind + 1} must be more then 0")
                println()
                return
            }
        }
        this.Ns = newNs
    }

    private fun printTaskInfo() {
        println("Task №5.2 Gauss quadrature formula, its nodes and coefficients")
        println(
            """
            Problem parameters: integration limits a, b and
            function f(x), N – number of nodes.
            The weight is considered p(x) = 1.
            - Calculate approximately and print the value of the integral of ρ(x)∙f(x)
            by [a, b] using GQS for different values of parameter N;
            - Get coefficients and nodes for N in 1..8, [a, b] = [-1, 1];
            - Check for polynomials for N from {3, 4, 5}.
            
            """.trimIndent()
        )
        printParameters()
        println()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                            -print command list")
        println("back                            -back ")
        println("evalAll                         -evaluate integral with all given Ns")
        println("coefsAndNodes                   -evaluate coefs and nodes for all given Ns")
        println("setParams                       -set all the parameters")
        println("printParams                     -print all the parameters")
        println("setNs                           -set N1, N2, N3 values")
        println("tests                           -start tests")
        println()
    }

    private fun printParameters() {
        println("Function: sqrt(x) * sin(x^2)")
        println("Sequence: $seq")
        println("Ns: $Ns")
    }

    private fun printErr() {
        println("[ERROR]: Wrong command")
        println("Print 'help' to see command list")
        println()
    }

    private fun calculateAndDisplay(isDefault: Boolean = true, withInfo: Boolean = true) {
        setDefault()
        setHomework()
        updateIntegralValue(isDefault)
        println("Real value of the integral: $integralValue")
        println()

        Ns.forEach { N ->
            val result = homework.calculateWithInfo(function, seq, N, withInfo)
            println("Calculated value: $result")
            println("Absolute error: ${homework.getAbsError(result, integralValue)}")
        }

        println()
    }

    private fun getCoefsAndNodes() {
        updateIntegralValue(true)
        Ns = List(8) { ind ->
            ind + 1
        }

        Ns.forEach { N ->
            homework.evaluateCoefsAndNodes(seq = Seq(-1.0, 1.0), N = N)
        }
    }

    private fun tests() {
        println("Tests: ")
        println()
        setSeq(0.0, 2.0)

        println("Seq [0; 2], f(x) = 6x^5, I = x^6")
        function = { x -> 6 * x.pow(5) }
        integral = { x -> x.pow(6) }
        setNs(mutableListOf(3))
        calculateAndDisplay(isDefault = false, withInfo = false)
        println()

        println("Seq [0; 2], f(x) = 8x^7, I = x^8")
        function = { x -> 8 * x.pow(7) }
        integral = { x -> x.pow(8) }
        setNs(mutableListOf(4))
        calculateAndDisplay(isDefault = false, withInfo = false)
        println()

        println("Seq [0; 2], f(x) = 10x^9, I = x^10")
        function = { x -> 10 * x.pow(9) }
        integral = { x -> x.pow(10) }
        setNs(mutableListOf(5))
        calculateAndDisplay(isDefault = false, withInfo = false)
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

                    calculateAndDisplay()
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
                "setNs" -> {
                    if (input.size != 4) {
                        printErr()
                        continue
                    }

                    val inputNs = input.drop(0).map { it.toInt() }
                    setNs(inputNs)
                }
                "coefsAndNodes" -> {
                    if (input.size > 1) {
                        printErr()
                        continue
                    }

                    getCoefsAndNodes()
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