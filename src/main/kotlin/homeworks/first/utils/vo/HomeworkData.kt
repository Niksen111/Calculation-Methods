package homeworks.first.utils.vo

import homeworks.utils.vo.Seq
import java.math.BigDecimal

data class HomeworkData(
    var func: (BigDecimal) -> (BigDecimal),
    var seq: Seq,
    var epsilon: BigDecimal,
    var firstDerivative: (BigDecimal) -> (BigDecimal),
    var secondDerivative: (BigDecimal) -> (BigDecimal)
)
