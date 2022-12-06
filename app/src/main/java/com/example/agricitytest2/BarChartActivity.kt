package com.example.agricitytest2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityBarchartBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


private const val TAG = "BAR ACTIVITY"

class BarChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarchartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBarchartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val parameter: String = intent.getStringExtra("Parameter").toString()
        val barChart = binding.barChart

//        val parametro: String = "temperature"
//        val experiencia: String = "http://agricity.ipleiria.pt/api/$parametro/1"
//        Log.d(TAG, "O URL é $experiencia")
//        val fetchData: FetchData = FetchData(experiencia)
//        var resultado: JSONArray = JSONArray()
//        if (fetchData.startFetch()) {
//            Log.d(TAG, "Começou o fetch")
//            if (fetchData.onComplete()) {
//                    resultado = JSONArray(fetchData.result)
//                    Log.d(TAG, resultado.toString())
//                    Log.d(TAG, "Completou o fetch")
//
//            } else {
//                Log.e(TAG, "Não Completou o fetch")
//            }
//        } else {
//            Log.e(TAG, "Nao fez fetch")
//        }
//        Log.d(TAG, "Chegamos ao fim")





        val resultado = APIContract.getAPIData(parameter, 1)
//
//        var visitors: ArrayList<BarEntry> = ArrayList<BarEntry>()
        val entries: MutableList<BarEntry> = ArrayList()

        val baseTimestamp = DateTimeToEpoch.dateTimeToEpochLong(resultado.getJSONObject(0).get("created_at").toString())

        for (i in 0 until resultado.length()) {
            val entrada = resultado.getJSONObject(i)
            //var date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entrada.get("created_at").toString())
            //Log.d(TAG, " ${entrada.get("valor")} by $date")

            //visitors.add(i, BarEntry(1f + i, i*2f))

            val data = DateTimeToEpoch.dateTimeToEpochFloat(entrada.get("created_at").toString())
            Log.d(TAG, " ${entrada.get("valor")} by $data")

            entries.add(BarEntry(data,entrada.get("valor").toString().toFloat()))
        }
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = TimestampXAxisFormatter(baseTimestamp)
//
//        var l = barChart.legend
//        l.formSize = 10f // set the size of the legend forms/shapes
//        l.form = Legend.LegendForm.CIRCLE // set what type of form/shape should be used
//        l.textSize = 12f
//        l.textColor = Color.BLACK
//        l.xEntrySpace = 5f // space between the legend entries on the x-axis
//        l.yEntrySpace = 5f // space between the legend entries on the y-axis
//        // set custom labels and colors
//
//        val xAxis: XAxis = barChart.getXAxis()
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.valueFormatter = MyXAxisValueFormatter()
//        xAxis.setLabelsToSkip(0)
//
//        val entries: MutableList<BarEntry> = ArrayList()
//        entries.add(BarEntry(0f, 30f))
//        entries.add(BarEntry(1f, 80f))
//        entries.add(BarEntry(2f, 60f))
//        entries.add(BarEntry(3f, 50f))
//        // gap of 2f
//        entries.add(BarEntry(5f, 70f))
//        entries.add(BarEntry(6f, 60f))

        val set: BarDataSet = BarDataSet(entries, "Temperatura")

        val dados: BarData = BarData(set)
        dados.barWidth = 0.9f // set custom bar width
        barChart.data = dados
        barChart.setFitBars(true) // make the x-axis fit exactly all bars
        barChart.invalidate() // refresh


    }

    internal class TimestampXAxisFormatter(var baseTimestamp: Long) :
        IndexAxisValueFormatter() {
        override fun getFormattedValue(value: Float): String {

            // Add base timestamp
            var timestamp = value.toLong()
            timestamp += baseTimestamp
            Log.d(TAG, "getFormattedValue, value : $value")
            Log.e(TAG, "getFormattedValue, Timestamp : $timestamp")

            // Convert from seconds back to milliseconds to format time  to show to the user
            return SimpleDateFormat("HH:mm:ss").format(Date(timestamp * 1000))
        }
    }



}