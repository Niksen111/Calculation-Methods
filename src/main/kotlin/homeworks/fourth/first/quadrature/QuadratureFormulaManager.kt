package homeworks.fourth.first.quadrature

import homeworks.fourth.first.quadrature.impl.*

internal class QuadratureFormulaManager {
    private val formulas = listOf(
        LeftRectangleQF(),
        MiddleRectangleQF(),
        RightRectangleQF(),
        SimpsonQF(),
        ThreeEightsQF(),
        TrapezoidQF()
    )

    private val formulasByName = formulas.associateBy { it.getName() }

    fun getFormulaByName(name: String) = formulasByName.getValue(name)

    fun getNames() = formulas.map { it.getName() }
}