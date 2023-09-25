package main

import homeworks.Ui
import homeworks.UiManager

private val uiManager = UiManager()

private val taskMap = mapOf(
   "1" to "UiH1"
)

private fun printInfo() {
   println("Calculation Methods")
   println()
}

private fun printCommandList() {
   println("Commands list:")
   println("help                                  -print command list")
   println("quit                                  -quit the app")
   println("taskList                              -print task list")
   println("task <number>                         -choose task")
   println()
}

private fun printTaskList() {
   println("Task list:")
   taskMap.map {
      println(it.key)
   }
}

private fun printErr() {
   println("[ERROR]: Wrong command")
   println()
}

private fun startUi() {
   printInfo()
   printCommandList()
   while (true) {
      println("Print 'help' to see command list")
      println("Enter new command:")
      try {
         val input = readln().split(' ')
         when (input[0]) {
            "help" -> {
               if (input.size != 1) {
                  printErr()
                  continue
               }

               printCommandList()
            }
            "quit" -> {
               if (input.size != 1) {
                  printErr()
                  continue
               }

               break
            }
            "taskList" -> {
               if (input.size != 1) {
                  printErr()
                  continue
               }

               printTaskList()
            }
            "task" -> {
               if (input.size != 2 || !taskMap.contains(input[1])) {
                  printErr()
                  continue
               }

               val ui = uiManager.getUi(taskMap[input[1]]!!)
               ui.start()
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

fun main(args: Array<String>) {
   startUi()
}