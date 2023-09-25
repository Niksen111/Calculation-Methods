package homeworks.first.utils.vo

import homeworks.utils.vo.Seq

data class HomeworkData(
    var func: (Double) -> (Double),
    var seq: Seq,
    var epsilon: Double,
    var firstDerivative: (Double) -> (Double),
    var secondDerivative: (Double) -> (Double)
)
