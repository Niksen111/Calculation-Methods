package homeworks.utils.vo

data class Seq(
    val left: Double,
    val right: Double
) {
    fun size() = right - left
}