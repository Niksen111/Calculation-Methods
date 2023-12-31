package homeworks.third.second

import homeworks.utils.ui.Ui

class UiH3P2 : Ui {
    private var homework = Homework3P2()

    private fun setHomework() {
        homework = Homework3P2()
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