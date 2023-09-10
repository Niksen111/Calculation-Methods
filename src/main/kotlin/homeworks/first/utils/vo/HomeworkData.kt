package homeworks.first.utils.vo

data class HomeworkData(
    var func: (Double) -> (Double),
    var seq: Seq,
    var epsilon: Double,
    var firstDerivative: (Double) -> (Double),
    var secondDerivative: (Double) -> (Double)
)
