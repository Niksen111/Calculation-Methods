package homeworks

interface Ui {
    fun start()

    fun getCode(): String = this.javaClass.simpleName
}