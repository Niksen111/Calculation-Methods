package homeworks.third.first

import homeworks.utils.ui.Ui

class UiH3P1 : Ui{
    private var homework = Homework3P1()

    private fun setHomework() {
        homework = Homework3P1()
    }

    private fun printTaskInfo() {
        println("Task № ########################")
        println("Option 10")
        println()
    }

    private fun printCommandList() {
        println("Commands list:")
        println("help                            -print command list")
        println("back                            -quit the current task")
        println("evalAll <point>                 -evaluate by all methods")
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
}