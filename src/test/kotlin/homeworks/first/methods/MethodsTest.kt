package homeworks.first.methods

import homeworks.first.FirstHomework
import homeworks.first.utils.vo.Seq
import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.test.assertEquals

/**
 * @author nksenchik
 * @since 1.0.0
 */
class MethodsTest {
    private fun roundToN(value: Double, n: Int): Double {
        val tenDegree = 10.0.pow(n.toDouble())

        return (value * tenDegree).roundToLong().toDouble() / tenDegree
    }

    private val methodsManager = MethodsManager()

    fun checkOnTaskData(methodName: String) {
        // Values
        val seq = Seq(-5.0, 5.0)
        val epsilon = 0.000001

        // Objects
        val func: (Double) -> (Double) = { x ->
            1.2 * x.pow(4) + 2 * x.pow(3) - 13 * x.pow(2) - 14.2 * x - 24.1
        }

        val firstDerivative: (Double) -> (Double) = { x ->
            4.8 * x.pow(3) + 6 * x.pow(2) - 26 * x - 14.2
        }

        val secondDerivative: (Double) -> (Double) = { x ->
            14.4 * x.pow(2) + 12 * x - 26
        }

        val homework = FirstHomework(func, seq, epsilon, firstDerivative, secondDerivative)
        val sequences = homework.separateSolutions(100000)
        val method = methodsManager.getMethod(methodName)

        // Execution
        val result = method.findSolution(func, sequences.first(), epsilon, firstDerivative, secondDerivative)

        // Verification
        assertEquals(-3.96719, roundToN(result.result, 5))
    }

    @Test
    fun `Bisection method returns right solution`() {
        checkOnTaskData("Bisection")
    }

    @Test
    fun `Newton method returns right solution`() {
        checkOnTaskData("Newton")
    }

    @Test
    fun `Modified Newton method returns right solution`() {
        checkOnTaskData("Modified Newton")
    }

    @Test
    fun `Secant method returns right solution`() {
        checkOnTaskData("Secant")
    }
}