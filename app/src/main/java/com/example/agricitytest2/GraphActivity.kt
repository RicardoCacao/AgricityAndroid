package com.example.agricitytest2


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityGraphBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.data.Entry as MikephilChartingDataEntry


class GraphActivity : AppCompatActivity() {

    lateinit var binding: ActivityGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parameter: String = intent.getStringExtra("Parameter").toString()
        Toast.makeText(this, parameter, Toast.LENGTH_SHORT).show()

        val chart = binding.chart

        var visitors: ArrayList<LineData>


        // the labels that should be drawn on the XAxis
        // the labels that should be drawn on the XAxis
        val quarters = arrayOf("Q1", "Q2", "Q3", "Q4")

        val formatter: ValueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                return quarters[value.toInt()]
            }
        }

        val xAxis: XAxis = chart.xAxis
        xAxis.granularity = 1f // minimum axis-step (interval) is 1

        xAxis.valueFormatter = formatter


        val valsComp1 = ArrayList<MikephilChartingDataEntry>()
        val valsComp2: ArrayList<MikephilChartingDataEntry> = ArrayList<MikephilChartingDataEntry>()

        val apiresult = APIContract.getAPIData(parameter, 1)



        val c1e1: MikephilChartingDataEntry =
            MikephilChartingDataEntry(0f, 100000f) // 0 == quarter 1

        valsComp1.add(c1e1)
        val c1e2: MikephilChartingDataEntry =
            MikephilChartingDataEntry(1f, 140000f) // 1 == quarter 2 ...

        valsComp1.add(c1e2)
        // and so on ...
        // and so on ...
        val c2e1: MikephilChartingDataEntry =
            MikephilChartingDataEntry(0f, 130000f) // 0 == quarter 1

        valsComp2.add(c2e1)
        val c2e2: MikephilChartingDataEntry =
            MikephilChartingDataEntry(1f, 115000f) // 1 == quarter 2 ...

        valsComp2.add(c2e2)

        val setComp1 = LineDataSet(valsComp1, "Humidade Exterior")
        setComp1.axisDependency = AxisDependency.LEFT
        val setComp2 = LineDataSet(valsComp2, "Humidade do solo")
        setComp2.axisDependency = AxisDependency.LEFT


        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(setComp1)
        dataSets.add(setComp2)
        val data = LineData(dataSets)
        chart.setData(data)
        chart.invalidate() // refresh


//        val entries: MutableList<Entry> = ArrayList()
//
//
//
//        /*val rand = Random
//
//        var entries = ArrayList<Entry>()
//            // turn your data into Entry objects
//        for (i in 0..100){
//            var entryX = rand.nextDouble(1.0, 65.2).toFloat()
//            var entryY = (i + 1).toFloat()
//            entries.add(i, Entry(entryX,entryY))
//        }*/
//
//        val dataSet = LineDataSet(entries, parameter); // add entries to dataset
//        dataSet.color = R.color.black;
//        dataSet.valueTextColor = R.color.holo_blue_light
//        dataSet.lineWidth = 8f
//
//        val lineData = LineData(dataSet)
//        chart.data = lineData
//        chart.invalidate() // refresh

    }


    data class Values(var timeStamp: Float, var yValue: Float) {
        fun set(valX: Float, valY: Float) {
            timeStamp = valX
            yValue = valY
        }

        fun getValueX(): Float {
            return timeStamp
        }

        fun getValueY(): Float {
            return yValue
        }
    }


}