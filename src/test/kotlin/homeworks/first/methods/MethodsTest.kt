package homeworks.first.methods

import homeworks.first.FirstHomework
import homeworks.first.FirstHomework.Companion.defaultData
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
        // Objects
        val homework = FirstHomework()
        val sequences = homework.separateSolutions(100000)
        val method = methodsManager.getMethod(methodName)

        // Execution
        val result = method.findSolution(
            defaultData.func,
            sequences.first(),
            defaultData.epsilon,
            defaultData.firstDerivative,
            defaultData.secondDerivative
        )

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
        checkOnTaskData("Modified_Newton")
    }

    @Test
    fun `Secant method returns right solution`() {
        checkOnTaskData("Secant")
    }
}