package homeworks.first.methods

import homeworks.first.Homework1
import homeworks.utils.vo.Seq
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.roundToLong


/**
 * @author nksenchik
 * @since 1.0.0
 */
class Homework1Test {
    private fun roundToN(value: Double, n: Int): Double {
        val tenDegree = 10.0.pow(n.toDouble())

        return (value * tenDegree).roundToLong().toDouble() / tenDegree
    }

    @Test
    internal fun `separateSolutions returns right seq list`() {
        // Objects
        val homework = Homework1()

        val realSequences1 = listOf(Seq(-4.0, -3.0), Seq(3.0, 4.0))
        val realSequences2 = listOf(Seq(-4.0, -3.9), Seq(3.2, 3.3))
        val realSequences3 = listOf(Seq(-3.97, -3.96), Seq(3.28, 3.29))
        val realSequences4 = listOf(Seq(-3.968, -3.967), Seq(3.287, 3.288))
        val realSequences5 = listOf(Seq(-3.9672, -3.9671), Seq(3.2879, 3.288))
        val realSequences6 = listOf(Seq(-3.96719, -3.96718), Seq(3.2879, 3.28791))

        // Execution
        val sequences1 = homework.separateSolutions(10)
            .map { Seq(roundToN(it.left, 6), roundToN(it.right, 6)) }
        val sequences2 = homework.separateSolutions(100)
            .map { Seq(roundToN(it.left, 6), roundToN(it.right, 6)) }
        val sequences3 = homework.separateSolutions(1000)
            .map { Seq(roundToN(it.left, 6), roundToN(it.right, 6)) }
        val sequences4 = homework.separateSolutions(10000)
            .map { Seq(roundToN(it.left, 6), roundToN(it.right, 6)) }
        val sequences5 = homework.separateSolutions(100000)
            .map { Seq(roundToN(it.left, 6), roundToN(it.right, 6)) }
        val sequences6 = homework.separateSolutions(1000000)
            .map { Seq(roundToN(it.left, 6), roundToN(it.right, 6)) }

        // Verification
        assertEquals(realSequences1, sequences1)
        assertEquals(realSequences2, sequences2)
        assertEquals(realSequences3, sequences3)
        assertEquals(realSequences4, sequences4)
        assertEquals(realSequences5, sequences5)
        assertEquals(realSequences6, sequences6)
    }
}