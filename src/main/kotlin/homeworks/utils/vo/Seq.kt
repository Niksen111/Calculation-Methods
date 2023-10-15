package homeworks.utils.vo

import java.math.BigDecimal

data class Seq(
    val left: BigDecimal,
    val right: BigDecimal
) {
    init {
        require(right >= left)
    }

    fun size() = right - left
}
