package homeworks.second.methods

import homeworks.second.methods.impl.LagrangeMethod
import homeworks.second.methods.impl.NewtonMethod

class InterpolationMethodsManager {
    private var methods: List<InterpolationMethod> = listOf(
        NewtonMethod(),
        LagrangeMethod()
    )

    private val methodsByMethodName =
        methods.associateBy { it.getMethodName() }

    fun getMethod(name: String): InterpolationMethod {
        return methodsByMethodName.getValue(name)
    }
}