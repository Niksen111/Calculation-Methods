package homeworks.utils.vo

data class Seq(
    val left: Double,
    val right: Double
) {
    init {
        require(right >= left)
    }

    fun size() = right - left

    override fun toString(): String {
        return "[$left; $right]"
    }
}
