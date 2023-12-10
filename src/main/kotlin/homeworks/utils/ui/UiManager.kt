package homeworks.utils.ui

import homeworks.fifth.second.UiH5P2
import homeworks.fifth.first.UiH5P1
import homeworks.first.UiH1
import homeworks.fourth.first.UiH4P1
import homeworks.fourth.second.UiH4P2
import homeworks.fourth.third.UiH4P3
import homeworks.second.UiH2
import homeworks.third.first.UiH3P1
import homeworks.third.second.UiH3P2

class UiManager {
    private val uiList = listOf(
        UiH1(),
        UiH2(),
        UiH3P1(),
        UiH3P2(),
        UiH4P1(),
        UiH4P2(),
        UiH4P3(),
        UiH5P1(),
        UiH5P2()
    )

    private val uiByUiCode = uiList.associateBy { it.getCode() }

    fun getUi(uiCode: String): Ui {
        return uiByUiCode.getValue(uiCode)
    }
}