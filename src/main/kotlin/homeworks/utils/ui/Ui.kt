package homeworks.utils.ui

interface Ui {
    fun start()

    fun getCode(): String = this.javaClass.simpleName
}