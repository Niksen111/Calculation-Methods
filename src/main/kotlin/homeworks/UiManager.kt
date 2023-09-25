package homeworks

import homeworks.first.UiH1
import homeworks.second.UiH2

class UiManager {
    private val uiList = listOf(
        UiH1(),
        UiH2()
    )

    private val uiByUiCode = uiList.associateBy { it.getCode() }

    fun getUi(uiCode: String): Ui {
        return uiByUiCode.getValue(uiCode)
    }
}