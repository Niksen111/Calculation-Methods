package homeworks.first.methods

import homeworks.first.Homework1
import homeworks.first.Homework1.Companion.defaultData
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.math.pow
import kotlin.test.assertEquals

/**
 * @author nksenchik
 * @since 1.0.0
 */
class MethodsTest {
    private val methodsManager = MethodsManager()

    fun checkOnTaskData(methodName: String) {
        // Objects
        val homework = Homework1()
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
        assertEquals((-3.96719).toBigDecimal(), result.result.setScale(5))
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