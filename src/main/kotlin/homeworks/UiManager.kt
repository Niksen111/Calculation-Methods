package homeworks

import homeworks.first.UiH1

class UiManager {
    private val uiList = listOf(
        UiH1()
    )

    private val uiByUiCode = uiList.associateBy { it.getCode() }

    fun getUi(uiCode: String): Ui {
        return uiByUiCode.getValue(uiCode)
    }
}