package homeworks.first.methods

import homeworks.first.Homework1
import homeworks.utils.vo.Seq
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.math.pow
import kotlin.math.roundToLong


/**
 * @author nksenchik
 * @since 1.0.0
 */
class Homework1Test {
    @Test
    internal fun `separateSolutions returns right seq list`() {
        // Objects
        val homework = Homework1()

        val realSequences1 = listOf(Seq((-4).toBigDecimal(), (-3).toBigDecimal()),
            Seq(3.toBigDecimal(), 4.toBigDecimal()))
        val realSequences2 = listOf(Seq((-4.0).toBigDecimal(), (-3.9).toBigDecimal()),
            Seq(3.2.toBigDecimal(), 3.3.toBigDecimal()))
        val realSequences3 = listOf(Seq((-3.97).toBigDecimal(), (-3.96).toBigDecimal()),
            Seq(3.28.toBigDecimal(), 3.29.toBigDecimal()))
        val realSequences4 = listOf(Seq((-3.968).toBigDecimal(), (-3.967).toBigDecimal()),
            Seq(3.287.toBigDecimal(), 3.288.toBigDecimal()))
        val realSequences5 = listOf(Seq((-3.9672).toBigDecimal(), (-3.9671).toBigDecimal()),
            Seq(3.2879.toBigDecimal(), 3.288.toBigDecimal().setScale(4)))
        val realSequences6 = listOf(Seq((-3.96719).toBigDecimal(), (-3.96718).toBigDecimal()),
            Seq(3.2879.toBigDecimal().setScale(5), 3.28791.toBigDecimal()))

        // Execution
        val sequences1 = homework.separateSolutions(10)
            .map { Seq(it.left.setScale(0), it.right.setScale(0)) }
        val sequences2 = homework.separateSolutions(100)
            .map { Seq(it.left.setScale(1), it.right.setScale(1)) }
        val sequences3 = homework.separateSolutions(1000)
            .map { Seq(it.left.setScale(2), it.right.setScale(2)) }
        val sequences4 = homework.separateSolutions(10000)
            .map { Seq(it.left.setScale(3), it.right.setScale(3)) }
        val sequences5 = homework.separateSolutions(100000)
            .map { Seq(it.left.setScale(4), it.right.setScale(4)) }
        val sequences6 = homework.separateSolutions(1000000)
            .map { Seq(it.left.setScale(5), it.right.setScale(5)) }

        // Verification
        assertEquals(realSequences1, sequences1)
        assertEquals(realSequences2, sequences2)
        assertEquals(realSequences3, sequences3)
        assertEquals(realSequences4, sequences4)
        assertEquals(realSequences5, sequences5)
        assertEquals(realSequences6, sequences6)
    }
}