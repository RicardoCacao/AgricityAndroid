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