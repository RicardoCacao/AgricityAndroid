package com.example.agricitytest2


import android.R
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityGraphBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random


class GraphActivity : AppCompatActivity() {

    lateinit var binding: ActivityGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parameter: String = intent.getStringExtra("Parameter").toString()
        Toast.makeText(this, parameter, Toast.LENGTH_SHORT).show()

        val chart = binding.chart as LineChart

        TODO("Implement data class for graph values")
        val rand = Random
        var objects: Array<Values> = arrayOf(Values(0f,0f))
        objects.fill(Values(0f,0f),0,100)

        for (i in 0..100){
            objects[i] = Values(rand.nextDouble(1.2,20.2).toFloat(),rand.nextDouble(1.0,20.2).toFloat())
        }


        var dataObjects: Array<Values> = objects

        val entries: MutableList<Entry> = ArrayList()

        for (data in dataObjects) {
            // turn your data into Entry objects
            entries.add(Entry(data.getValueX(), data.getValueY()))
        }


        /*val rand = Random

        var entries = ArrayList<Entry>()
            // turn your data into Entry objects
        for (i in 0..100){
            var entryX = rand.nextDouble(1.0, 65.2).toFloat()
            var entryY = (i + 1).toFloat()
            entries.add(i, Entry(entryX,entryY))
        }*/

        val dataSet = LineDataSet(entries, parameter); // add entries to dataset
        dataSet.color = R.color.black;
        dataSet.valueTextColor = R.color.holo_blue_light
        dataSet.lineWidth = 8f

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate() // refresh

    }

    data class Values(var timeStamp: Float, var yValue: Float){
        fun set(valX: Float, valY: Float){
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