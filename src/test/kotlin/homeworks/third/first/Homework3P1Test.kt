package homeworks.third.first

import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.test.assertEquals

class Homework3P1Test {
    @Test
    fun `first method`() {
        // Objects
        val homework = Homework3P1()

        // Execution
        val result1 = homework.evalByFirstMethod(1.0)
        val result2 = homework.evalByFirstMethod(2.0)

        // Verification
        assertEquals(0.0, result1)
        assertEquals(0.5824380904.toBigDecimal(), result2.toBigDecimal().setScale(10, RoundingMode.DOWN))
    }

    @Test
    fun `second method`() {
        // Objects
        val homework = Homework3P1()

        // Execution
        val result1 = homework.evalBySecondMethod(1.0)
        val result2 = homework.evalBySecondMethod(2.0)

        // Verification
        assertEquals(BigDecimal.ZERO.setScale(15, RoundingMode.HALF_DOWN),
            result1.toBigDecimal().setScale(15, RoundingMode.HALF_DOWN))
        assertEquals(0.5824379427.toBigDecimal(), result2.toBigDecimal().setScale(10, RoundingMode.DOWN))
    }
}