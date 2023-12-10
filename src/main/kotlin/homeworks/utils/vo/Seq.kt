package homeworks.utils.vo

data class Seq(
    val left: Double,
    val right: Double
) {
    init {
        require(right >= left)
    }

    constructor(left: Number, right: Number) : this(left.toDouble(), right.toDouble())

    val size = right - left
    val middle = (right + left) / 2

    override fun toString(): String {
        return "[$left; $right]"
    }
}
