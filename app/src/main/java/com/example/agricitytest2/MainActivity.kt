package com.example.agricitytest2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityMainBinding
import com.vishnusivadas.advanced_httpurlconnection.FetchData
import kotlin.random.Random

private const val TAG = "MAIN ACTIVITY"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
        val parametro: String = "temperature"
        val experiencia: String = "http://agricity.ipleiria.pt/api/$parametro/1"
        Log.d(TAG, "O URL é $experiencia")
        val fetchData: FetchData = FetchData(experiencia)
        if (fetchData.startFetch()) {
            Log.d(TAG, "Começou o fetch")
            if (fetchData.onComplete()) {
                try {
                    val resultado = fetchData.result
                    Log.d(TAG, resultado)
                    Log.d(TAG, "Completou o fetch")
                }catch (e: Exception){
                    Log.e(TAG,"We are in the beam" + e.message.toString())
                    val resultado = ""
                }
            } else {
                Log.e(TAG, "Não Completou o fetch")
            }
        } else {
            Log.e(TAG, "Nao fez fetch")
        }
        Log.d(TAG, "Chegamos ao fim")

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


        val eheheh: ImageButton = binding.menuButton
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

    override fun onClick(v: View) {
        when (v.id) {
            binding.temperatureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "temperature")
                startActivity(goToGraphActivity)
//                finish()
            }
            binding.humidityCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "humidity")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.pressureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "pressure")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.rain24HrsCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "rain24hrs")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.windSpeedCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "windspeed")
                startActivity(goToGraphActivity)
//              finish()
            }
        }

    }

}