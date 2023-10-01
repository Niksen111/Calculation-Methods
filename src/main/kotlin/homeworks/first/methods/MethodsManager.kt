package homeworks.first.methods

import homeworks.first.methods.impl.BisectionMethod
import homeworks.first.methods.impl.ModifiedNewtonMethod
import homeworks.first.methods.impl.NewtonMethod
import homeworks.first.methods.impl.SecantMethod

class MethodsManager {
    private var methods: List<Method> = listOf(
        BisectionMethod(),
        NewtonMethod(),
        ModifiedNewtonMethod(),
        SecantMethod()
    )

    private val methodsByMethodName by lazy {
        methods.associateBy { it.getMethodName() }
    }

    fun getMethod(name: String): Method {
        return methodsByMethodName.getValue(name)
    }
}