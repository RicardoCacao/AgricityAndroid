package com.example.agricitytest2

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import com.example.agricitytest2.databinding.ActivityGraphBinding
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import kotlin.math.roundToInt
import kotlin.random.Random


class GraphActivity : AppCompatActivity() {

    lateinit var binding: ActivityGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parameter: String = intent.getStringExtra("Parameter").toString()



        // initialize our XYPlot reference:
        val plot = binding.plot

        // create a couple arrays of y-values to plot:

        binding.plot.domainTitle.text = getString(R.string.domain, parameter)

        val domainLabels = arrayOf<Number>(1, 2, 3, 6, 7, 8, 9, 10, 13, 14)

        val rand = Random(30)

        var prim = rand.nextInt(30)
        var seg = rand.nextInt(30)
        var ter = rand.nextInt(30)
        var qua = rand.nextInt(30)
        var qui = rand.nextInt(30)
        var sex = rand.nextInt(30)
        var set = rand.nextInt(30)
        var oit = rand.nextInt(30)
        var nov = rand.nextInt(30)
        var dez = rand.nextInt(30)

        val series1Numbers = arrayOf<Number>(prim, seg, ter, qua, qui, sex, set, oit, nov, dez)
//        val series2Numbers = arrayOf<Number>(5, 2, 10, 5, 20, 10, 40, 20, 80, 40)

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        val series1: XYSeries = SimpleXYSeries(
            listOf(*series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1"
        )
//        val series2: XYSeries = SimpleXYSeries(
//            listOf(*series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2"
//        )

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        val series1Format = LineAndPointFormatter(Color.WHITE, Color.BLUE, null, null)
//        val series2Format = LineAndPointFormatter(Color.RED, Color.GREEN, null, null)

        // add an "dash" effect to the series2 line:
       /* series2Format.linePaint.pathEffect = DashPathEffect(
            floatArrayOf( // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20f),
                PixelUtils.dpToPix(15f)
            ), 0F
        )
*/
        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.interpolationParams =
            CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)
//        series2Format.interpolationParams =
//            CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format)
//        plot.addSeries(series2, series2Format)
        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(
                obj: Any,
                toAppendTo: StringBuffer,
                pos: FieldPosition
            ): StringBuffer {
                val i = (obj as Number).toFloat().roundToInt()
                return toAppendTo.append(domainLabels[i])
            }

            override fun parseObject(source: String, pos: ParsePosition): Any? {
                return null
            }
        }

    }



}