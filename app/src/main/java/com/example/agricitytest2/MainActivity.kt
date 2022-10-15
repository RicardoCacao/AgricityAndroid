package com.example.agricitytest2

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import com.example.agricitytest2.databinding.ActivityMainBinding
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9)
            val series1Number= arrayOf<Number>(1,4,8,12,16,32,26,29,10,13)
            val series2Number = arrayOf<Number>(5, 2, 10, 5, 20, 10, 40, 20, 80, 40)

            val series1 : XYSeries = SimpleXYSeries(listOf(* series1Number), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Series 1")
            val series2 : XYSeries = SimpleXYSeries(listOf(* series2Number), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Series 2")

            val series1Format = LineAndPointFormatter(Color.BLUE,Color.BLACK,Color.YELLOW,null)
            val series2Format = LineAndPointFormatter(Color.GREEN,Color.RED,Color.GRAY,null)


            series2Format.linePaint.pathEffect = DashPathEffect(
            floatArrayOf(
                // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20f),
                PixelUtils.dpToPix(15f)
            ), 0F)

            val plot = binding.plot

            series1Format.interpolationParams = CatmullRomInterpolator.Params(10,CatmullRomInterpolator.Type.Centripetal)

            plot.addSeries(series1,series1Format)
            plot.addSeries(series2,series2Format)

            plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format(){
                override fun format(
                    obj: Any?,
                    toAppendTo: StringBuffer,
                    pos: FieldPosition,

                ) : StringBuffer {
                    val i = (obj as Number).toFloat().roundToInt()
                    return toAppendTo.append(domainLabels[i])

                }

                override fun parseObject(source: String?, pos: ParsePosition): Any?{
                    return null
                }

            }

    }

    public override fun onStart() = super.onStart()
    public override fun onResume() = super.onResume()
    public override fun onPause() = super.onPause()
    public override fun onDestroy() = super.onDestroy()
    public override fun onRestart() = super.onRestart()
}


