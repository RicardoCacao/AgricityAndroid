package com.example.agricitytest2


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityGraphBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.github.mikephil.charting.data.Entry as MikephilChartingDataEntry

private const val TAG = "GRAPH ACTIVITY"

class GraphActivity : AppCompatActivity() {

    lateinit var binding: ActivityGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parameter: String = intent.getStringExtra("Parameter").toString()
        //Toast.makeText(this, parameter, Toast.LENGTH_SHORT).show()

        val chart = binding.chart

        var visitors: ArrayList<LineData>

        val apiResult = APIContract.getAPIData(parameter, 1)
        if (apiResult == JSONArray()) {
            Toast.makeText(this, "Error retrieving data from API", Toast.LENGTH_SHORT).show()
        }

        var entries: MikephilChartingDataEntry = MikephilChartingDataEntry()
        val valsComp1 = ArrayList<MikephilChartingDataEntry>()
        //val valsComp2 = ArrayList<MikephilChartingDataEntry>()


        // the labels that should be drawn on the XAxis
        val quarters: MutableList<String> = mutableListOf<String>()

        for (i in 0 until apiResult.length()) {
            val entrada = apiResult.getJSONObject(i)
            //var date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entrada.get("created_at").toString())
            //Log.d(TAG, " ${entrada.get("valor")} by $date")

            //visitors.add(i, BarEntry(1f + i, i*2f))
            val data = DateTimeToEpoch.dateTimeToEpochFloat(entrada.get("created_at").toString())

            quarters.add(data.toString())
            Log.d(TAG, " ${entrada.get("valor")} by $data")

            entries = MikephilChartingDataEntry(data, entrada.get("valor").toString().toFloat())
            valsComp1.add(entries)
            //entries.add(BarEntry(data,entrada.get("valor").toString().toFloat()))
        }

        val formatter: ValueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                return quarters[value.toInt()]
            }
        }


//        val c1e1: MikephilChartingDataEntry =
//            MikephilChartingDataEntry(0f, 100000f) // 0 == quarter 1
//
//        valsComp1.add(c1e1)
//
//        val c1e2: MikephilChartingDataEntry =
//            MikephilChartingDataEntry(1f, 140000f) // 1 == quarter 2 ...
//
//        valsComp1.add(c1e2)
//        // and so on ...
//        val c2e1: MikephilChartingDataEntry =
//            MikephilChartingDataEntry(0f, 130000f) // 0 == quarter 1

//        valsComp2.add(c2e1)
//        val c2e2: MikephilChartingDataEntry =
//            MikephilChartingDataEntry(1f, 115000f) // 1 == quarter 2 ...
//
//        valsComp2.add(c2e2)


        val xAxis: XAxis = chart.xAxis
        xAxis.granularity = 1f // minimum axis-step (interval) is 1



        val resultado = APIContract.getAPIData(parameter, 1)

        val baseTimestamp = DateTimeToEpoch.dateTimeToEpochLong(
            resultado.getJSONObject(0).get("created_at").toString()
        )
        val lastTimestamp = DateTimeToEpoch.dateTimeToEpochFloat(
            resultado.getJSONObject(resultado.length() - 1).get("created_at").toString()
        )
        val lengthOfArray = resultado.length() - 1
        xAxis.valueFormatter = TimestampXAxisFormatter(baseTimestamp)

        xAxis.axisMinimum = baseTimestamp.toFloat()
        xAxis.axisMaximum = lastTimestamp
        xAxis.setLabelCount(lengthOfArray, true)


        val setComp1 = LineDataSet(valsComp1, "Humidade Exterior")
        setComp1.axisDependency = AxisDependency.LEFT
        //val setComp2 = LineDataSet(valsComp2, "Humidade do solo")
        //setComp2.axisDependency = AxisDependency.LEFT


        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(setComp1)
        //dataSets.add(setComp2)
        val data = LineData(dataSets)
        try {
            chart.data = data
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }

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


    internal class TimestampXAxisFormatter(var baseTimestamp: Long) :
        IndexAxisValueFormatter() {
        override fun getFormattedValue(value: Float): String {

            // Add base timestamp
            var timestamp = value.toLong()
            timestamp += baseTimestamp
//            Log.d(TAG, "getFormattedValue, value : $value")
//            Log.d(TAG, "getFormattedValue, Timestamp : $timestamp")

            // Convert from seconds back to milliseconds to format time  to show to the user
            return SimpleDateFormat("HH:mm").format(Date(timestamp * 1000))
        }
    }


//    data class Values(var timeStamp: Float, var yValue: Float) {
//        fun set(valX: Float, valY: Float) {
//            timeStamp = valX
//            yValue = valY
//        }
//
//        fun getValueX(): Float {
//            return timeStamp
//        }
//
//        fun getValueY(): Float {
//            return yValue
//        }
//    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }
}


