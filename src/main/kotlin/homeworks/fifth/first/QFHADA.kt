package homeworks.fifth.first

import dnl.utils.text.table.TextTable
import homeworks.first.Homework1
import homeworks.fourth.second.CompoundQuadratureFormula
import homeworks.utils.vo.Seq
import org.apache.commons.math3.linear.Array2DRowRealMatrix
import org.apache.commons.math3.linear.MatrixUtils.inverse
import org.apache.commons.math3.linear.RealMatrix
import java.lang.String.format
import kotlin.math.abs
import kotlin.math.pow

class QFHADA(
    private val seq: Seq,
    private val n: Int,
    private val p: (Double) -> Double,
    private val pString: String,
    private val f: (Double) -> Double,
    private val fString: String,
    private val integral: (Seq) -> Double
) {
    private var sm = 0.0
    private var absErr = 0.0
    private var A: RealMatrix
    private var nodes: RealMatrix
    private var moments: RealMatrix
    private var simpleErr = 0.0
    private var simpleSm = 0.0
    private val m = 10000

    init {
        val momentsArr = DoubleArray(2 * n)
        for (i in 0 until 2 * n) {
            val func = { x: Double -> p(x) * x.pow(i) }
            momentsArr[i] = CompoundQuadratureFormula.calculate(func, seq, m)
        }

        val momentsData = Array(n) { DoubleArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                momentsData[i][j] = momentsArr[i + j]
            }
        }

        val momentsMinusVec = Array(n) { DoubleArray(1) }
        for (i in n until 2 * n) {
            momentsMinusVec[i - n][0] = -momentsArr[i]
        }

        val momentsInverse: RealMatrix = inverse(Array2DRowRealMatrix(momentsData))
        val momentsMinus: RealMatrix = Array2DRowRealMatrix(momentsMinusVec)
        val aVec: RealMatrix = momentsInverse.multiply(momentsMinus)
        val func = { x: Double ->
            var result = 0.0
            for (i in 0 until n) {
                result += aVec.getEntry(i, 0) * x.pow(i)
            }
            result += x.pow(n)
            result
        }

        val homework1 = Homework1(func, seq, 1e-10)
        val nodes: List<Double> = homework1.separateSolutions(1000000)
            .map { homework1.findSolutionByMethod("Secant", it).result }
        nodes.sorted()
        this.nodes = Array2DRowRealMatrix(nodes.toDoubleArray())
        val nodesData = Array(n) { DoubleArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                nodesData[i][j] = nodes[j].pow(i.toDouble())
            }
        }

        val nodesInverse: RealMatrix = inverse(Array2DRowRealMatrix(nodesData))
        val moments0ToNMinus1 = Array(n) { DoubleArray(1) }
        for (i in 0 until n) {
            moments0ToNMinus1[i][0] = momentsArr[i]
        }

        moments = Array2DRowRealMatrix(moments0ToNMinus1)
        this.A = nodesInverse.multiply(moments)
        for (i in 0 until n) {
            this.sm += A.getEntry(i, 0) * this.f(nodes[i])
        }

        absErr = abs(this.integral(seq) - sm)
    }

    private fun doSimpleQFha(): List<DoubleArray> {
        val simpleNodes = DoubleArray(n)
        var xI = seq.left
        val h = seq.size / (n - 1)
        for (i in 0 until n) {
            simpleNodes[i] = xI
            xI += h
        }

        val simpleMoments = DoubleArray(n)
        for (i in 0 until n) {
            val func = { x: Double -> p(x) * x.pow(i) }
            simpleMoments[i] = CompoundQuadratureFormula.calculate(func, seq, m)
        }

        val simpleNodesData = Array(n) { DoubleArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                simpleNodesData[i][j] = simpleNodes[j].pow(i.toDouble())
            }
        }

        val simpleNodesMatrix: RealMatrix = Array2DRowRealMatrix(simpleNodesData)
        val simpleMomentsData = Array(n) { DoubleArray(1) }
        for (i in 0 until n) {
            simpleMomentsData[i][0] = simpleMoments[i]
        }

        val simpleMomentsMatrix: RealMatrix = Array2DRowRealMatrix(simpleMomentsData)
        val simpleA: RealMatrix = inverse(simpleNodesMatrix).multiply(simpleMomentsMatrix)
        val simpleAData = DoubleArray(n)
        for (i in 0 until n) {
            simpleAData[i] = simpleA.getEntry(i, 0)
        }

        var simpleSm = 0.0
        for (i in 0 until n) {
            simpleSm += simpleA.getEntry(i, 0) * f(simpleNodes[i])
        }

        this.simpleSm = simpleSm
        simpleErr = abs(integral(seq) - simpleSm)
        return listOf(simpleMoments, simpleNodes, simpleAData)
    }

    fun printInfo() {
        println("Отрезок интегрирования: $seq")
        println("Количство узлов: $n")
        println(
            """
            ${"Весовая функция: $pString"}
            f(x) = $fString
            """.trimIndent()
        )
        println("Точное значение интеграла: ${integral(seq)}")
        val simpleTableColumns = arrayOf("i", "Моменты", "Узлы x_i", "Коэффициенты A_i")
        val simpleQFha = doSimpleQFha()
        val simpleMoments = simpleQFha[0]
        val simpleNodes = simpleQFha[1]
        val simpleA = simpleQFha[2]
        val simpleTableData = Array(n) {
            arrayOfNulls<String>(
                4
            )
        }
        for (i in 0 until n) {
            simpleTableData[i][0] = i.toString()
            // format double to 12 symbols after dot
            simpleTableData[i][1] = String.format("%.12f", simpleMoments[i])
            simpleTableData[i][2] = String.format("%.12f", simpleNodes[i])
            simpleTableData[i][3] = String.format("%.12f", simpleA[i])
        }
        val simpleTable = TextTable(simpleTableColumns, simpleTableData)
        println("Моменты, узлы и коэффициенты для простой КФ: ")
        simpleTable.printTable()
        //format with exponential notation
        println("Абсолютная погрешность для простой КФ: " + String.format("%.12e", simpleErr))
        println("Значение интеграла для простой КФ: " + String.format("%.12f", simpleSm))
        val tableColumns = arrayOf("i", "Моменты", "Узлы x_i", "Коэффициенты A_i")
        val tableData = Array(n) {
            arrayOfNulls<String>(
                4
            )
        }
        for (i in 0 until n) {
            tableData[i][0] = i.toString()
            // format double to 12 symbols after dot
            tableData[i][1] = format("%.12f", moments.getEntry(i, 0))
            tableData[i][2] = format("%.12f", nodes.getEntry(i, 0))
            tableData[i][3] = format("%.12f", A.getEntry(i, 0))
        }
        val table = TextTable(tableColumns, tableData)
        println("Моменты, узлы и коэффициенты для НАСТ КФ: ")
        table.printTable()
        println("Абсолютная погрешность для НАСТ КФ: " + String.format("%.12e", absErr))
        println("Значение интеграла для НАСТ КФ: " + String.format("%.12f", sm))
    }
}