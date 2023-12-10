package homeworks.fifth.first

import homeworks.utils.ui.Ui
import homeworks.utils.vo.Seq
import kotlin.math.*

class UiH5P1 : Ui {
    private var homework = Homework5P1()
    private var seq = Seq(0, 1)
    private var n = 6

    private fun setHomework() {
        this.homework = Homework5P1(
        )
    }

    private fun setSeq(left: Double, right: Double) {
        if (left >= right) {
            println("[ERROR]: Failed to set up sequence")
            println("[ERROR]: Left bound must be less then right")
            println()
            return
        }

        if (right > 1) {
            println("[ERROR]: Failed to set up sequence")
            println("[ERROR]: Right bound must be not more then 1")
            println()
            return
        }

        this.seq = Seq(left, right)
    }

    private fun setN(n: Int) {
        if (n < 1) {
            println("[ERROR]: Failed to set up m")
            println("[ERROR]: M must be more then 0")
            println()
            return
        }

        this.n = n
    }

    private fun printTaskInfo() {
        println(
            """
                Задание 5.1 по вычислительному практикуму.
                Приближённое вычисление интегралов при помощи квадратурных формул Наивысшей Алгебраической Степени Точности (КФ НАСТ)
                """.trimIndent()
        )
        println("Вариант 10")
        println("f(x) = sin(x)")
        println("p(x) = sqrt(1 - x)")
        println(
            """
            Теоретический блок:
                1. знать, что такое Алгебраическая Степень Точности КФ, двустороннюю оценку для АСТ ИКФ
                в случае знакопостоянного веса;
                2. знать, чему равна наивысшая АСТ КФ с N узлами;
                3. знать формулировку теоремы о КФ гауссова типа (или КФ НАСТ);
                4. знать алгоритм построения КФ НАСТ и алгоритм построения ИКФ.

            Ответы на теоретический блок:
                1. Алгебраическая степень точности квадратурной формулы – это максимальная степень многочлена,
                 для которой квадратурная формула точна для всех многочленов степени не выше этой. 
                2. d = 2N-1
                3. Теорема 1
                   Для того, чтобы АСТ КФ (1) = 2𝑁 − 1, необходимо и достаточно:
                    1. 𝜔𝑁 (𝑥) = ∏︀[k=1->N](𝑥 − 𝑥𝑘), ∫︁[𝑎,𝑏] 𝜌(𝑥)𝜔𝑁(𝑥)𝑄𝑁−1(𝑥)d𝑥 = 0 ∀𝑄𝑁−1 (2)
                    (𝜔𝑁 ⊥[𝜌, (𝑎,𝑏)] 𝑄𝑁−1 ⇐⇒ 𝜔𝑁 ⊥[𝜌,(𝑎,𝑏)] 1, 𝑥, . . . , 𝑥𝑁−1)
                    2. КФ (1) — интерполяционная
                4. ...
                
            Практический блок:
            Параметры задачи: пределы интегрирования – 𝑎, 𝑏 (запрашивать у пользователя; вводятся с клавиатуры), функции 𝜌(𝑥) и 𝑓(𝑥) (описать в коде программы).
                1. Найти "точное"значение I = ∫︀ по a, b  𝜌(𝑥)𝑓(𝑥) d𝑥 (при помощи матпакета или библиотеки), вывести егона печать.
                2. Написать программу, позволяющую вычислить приближенно I при помощи ИКФ с 𝑁 узлами (минимум, с 2-мя).
                3. Выводить на печать все промежуточные вычисления: моменты весовой функции, найденные
                узлы и коэффициенты построенной ИКФ.
                4. Сделать проверку на точность ИКФ на многочлене степени 𝑁 − 1, если число узлов КФ равно 𝑁.
                5. Вывести полученное значение интеграла (не менее 12 знаков после запятой).
                6. Сравнить полученное при помощи ИКФ значение с "точным"значением из матпакета.
                7. Написать программу, позволяющую вычислить приближенно I при помощи КФНАСТ 𝑁 (минимум, с 2-мя узлами).
                8. Выводить на печать все промежуточные вычисления: моменты весовой функции, найденный ортогональный многочлен, узлы и коэффициенты построенной КФ НАСТ.
                9. Сделать проверку на коэффициенты и точность КФ на одночлене 𝑥^3 (для двух узлов) и на одночлене 𝑥^(2𝑁−1), если число узлов равно 𝑁.
                10. Вывести полученное значение интеграла (не менее 12 знаков после запятой).
                11. Сравнить полученное при помощи КФНАСТ значение с "точным"значением из матпакета.
            
            """.trimIndent()
        )
        println()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                                  -print command list")
        println("back                                  -quit the current task")
        println("setSeq <left bound> <right bound>     -set up the sequence")
        println("setN <N>                              -set N value")
        println("tests                                 -run tests")
        println()
    }

    private fun printErr() {
        println("[ERROR]: Wrong command")
        println()
    }

    private fun tests() {
        val seq1 = this.seq

        var qfhada = QFHADA(seq1, 2, Homework5P1.p, "sqrt(1 - x)", Homework5P1.f, "sin(x)",
            Homework5P1.pseudoIntegral)
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 3, Homework5P1.p, "sqrt(1 - x)", Homework5P1.f, "sin(x)",
            Homework5P1.pseudoIntegral)
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 5, Homework5P1.p, "sqrt(1 - x)", Homework5P1.f, "sin(x)",
            Homework5P1.pseudoIntegral)
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, n, Homework5P1.p, "sqrt(1 - x)", Homework5P1.f, "sin(x)",
            Homework5P1.pseudoIntegral)
        qfhada.printInfo()
        println()
        val exponent = { x: Double -> exp(x) }
        qfhada = QFHADA(seq1, 2, exponent, "exp(x)", { x -> sin(x) }, "sin(x)",
            { seq : Seq -> 0.5 * exp(seq.right) * (sin(seq.right) - cos(seq.right)) - 0.5 * exp(seq.left) * (sin(seq.left) - cos(seq.left)) })
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 3, exponent, "exp(x)", { x -> sin(x) }, "sin(x)",
            { seq : Seq -> 0.5 * exp(seq.right) * (sin(seq.right) - cos(seq.right)) - 0.5 * exp(seq.left) * (sin(seq.left) - cos(seq.left)) })
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 5, exponent, "exp(x)", { x -> sin(x) }, "sin(x)",
            { s : Seq -> 0.5 * exp(s.right) * (sin(s.right) - cos(s.right)) - 0.5 * exp(s.left) * (sin(s.left) - cos(s.left)) })
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 3, exponent, "exp(x)", { x -> x }, "x",
            { s: Seq -> exp(s.right)*(s.right-1) - exp(s.left)*(s.left-1)})
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 3, exponent, "exp(x)", { x -> x.pow(2) }, "x^2",
            { 0.718281828459045 })
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 3, exponent, "exp(x)", { x -> x.pow(3) }, "x^3",
            { 0.5634363430819096 })
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 3, exponent, "exp(x)", { x -> x.pow(5) }, "x^5",
            { 0.3955995478020096 })
        qfhada.printInfo()
        println()
        qfhada = QFHADA(seq1, 3, exponent, "exp(x)", { x -> x.pow(6) }, "x^6",
            { 0.3446845416469874 })
        qfhada.printInfo()
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

                        val left: Double = input[1].toDouble()
                        val right: Double = input[2].toDouble()
                        setSeq(left, right)
                    }
                    "setN" -> {

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
}