package com.example.agricitytest2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




/*
        // initialize our XYPlot reference:
        val plot = binding.plot

        // create a couple arrays of y-values to plot:
        val domainLabels = arrayOf<Number>(1, 2, 3, 6, 7, 8, 9, 10, 13, 14)
        val series1Numbers = arrayOf<Number>(1, 4, 2, 8, 4, 16, 8, 32, 16, 64)
        val series2Numbers = arrayOf<Number>(5, 2, 10, 5, 20, 10, 40, 20, 80, 40)

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        val series1: XYSeries = SimpleXYSeries(
            listOf(*series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1"
        )
        val series2: XYSeries = SimpleXYSeries(
            listOf(*series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2"
        )

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        val series1Format = LineAndPointFormatter(Color.YELLOW, Color.GREEN, null, null)
        val series2Format = LineAndPointFormatter(Color.RED, Color.GREEN, null, null)

        // add an "dash" effect to the series2 line:
        series2Format.linePaint.pathEffect = DashPathEffect(
            floatArrayOf( // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20f),
                PixelUtils.dpToPix(15f)
            ), 0F
        )

        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.interpolationParams =
            CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)
        series2Format.interpolationParams =
            CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format)
        plot.addSeries(series2, series2Format)
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
*/
        val temperatureCard = binding.temperatureCard
        val humidityCard = binding.humidityCard
        val pressureCard = binding.pressureCard
        val rain24HrsCard = binding.rain24HrsCard
        val windSpeedCard = binding.windSpeedCard


        temperatureCard.setOnClickListener(this)
        humidityCard.setOnClickListener(this)
        pressureCard.setOnClickListener(this)
        rain24HrsCard.setOnClickListener(this)
        windSpeedCard.setOnClickListener(this)


        val submit: ImageButton = binding.logOutButton
        submit.setOnClickListener {
            val returnToLogin = Intent(applicationContext, Login::class.java)
            startActivity(returnToLogin)
            finish()
        }

        var temperature: String
        var humidity: String
        var rain: String
        var windspeed: String
        var pressure: String
        val windDir = getString(R.string.windDirectionValue)

        val rand = Random


        temperature = rand.nextInt(16).toString()
        humidity = rand.nextInt(16).toString()
        rain = rand.nextInt(16).toString()
        windspeed = rand.nextInt(16).toString()
        pressure = rand.nextInt(16).toString()

        binding.textViewTempValue.text = getString(R.string.tempValue, temperature)
        binding.textViewHumidityValue.text = getString(R.string.humidityValue, humidity)
        binding.textViewRain24hrValue.text = getString(R.string.rain24hrValue, rain)
        binding.textViewWindSpeedValue.text = getString(R.string.windSpeedValue, windspeed)
        binding.textViewWindDirValue.text = getString(R.string.windDirValue, windDir)
        binding.textViewPressureValue.text = getString(R.string.pressureValue, pressure)



        val eheheh: ImageButton = binding.backButton
        eheheh.setOnClickListener {

            temperature = rand.nextInt(16).toString()
            humidity = rand.nextInt(16).toString()
            rain = rand.nextInt(16).toString()
            windspeed = rand.nextInt(16).toString()
            pressure = rand.nextInt(16).toString()

            binding.textViewTempValue.text = getString(R.string.tempValue, temperature)
            binding.textViewHumidityValue.text = getString(R.string.humidityValue, humidity)
            binding.textViewRain24hrValue.text = getString(R.string.rain24hrValue, rain)
            binding.textViewWindSpeedValue.text = getString(R.string.windSpeedValue, windspeed)
            binding.textViewPressureValue.text = getString(R.string.pressureValue, pressure)
        }


        /* submit.setOnClickListener{
             val dbConnection: MySQLDbConnection
             dbConnection = MySQLDbConnection
             dbConnection.getConnection()
             dbConnection.executeMySQLQuery()

         }*/
    }

    override fun onClick(v: View){
        when (v.id) {
            binding.temperatureCard.id -> {
                val goToGraphActivity = Intent(applicationContext,GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter","Temperatura")
                startActivity(goToGraphActivity)
//                finish()
            }
            binding.humidityCard.id -> {
                val goToGraphActivity = Intent(applicationContext,GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter","Humidade")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.pressureCard.id -> {
                val goToGraphActivity = Intent(applicationContext,GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter","Pressão")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.rain24HrsCard.id -> {
                val goToGraphActivity = Intent(applicationContext,GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter","Chuva")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.windSpeedCard.id -> {
                val goToGraphActivity = Intent(applicationContext,GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter","Velocidade do vento")
                startActivity(goToGraphActivity)
//              finish()
            }
        }

    }

}