package homeworks.utils.ui

import homeworks.first.UiH1
import homeworks.fourth.first.UiH4P1
import homeworks.second.UiH2
import homeworks.third.first.UiH3P1
import homeworks.third.second.UiH3P2

class UiManager {
    private val uiList = listOf(
        UiH1(),
        UiH2(),
        UiH3P1(),
        UiH3P2(),
        UiH4P1()
    )

    private val uiByUiCode = uiList.associateBy { it.getCode() }

    fun getUi(uiCode: String): Ui {
        return uiByUiCode.getValue(uiCode)
    }
}