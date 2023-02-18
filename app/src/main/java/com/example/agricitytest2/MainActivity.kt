package com.example.agricitytest2

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random


private const val TAG = "MAIN ACTIVITY"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //testInsert()

        //onStationRefresh()

        displayStations(APIContract.getStations())

        //  CODE THAT RETURNED USER TO LOGIN SCREEN. NOW DEFUNCT SINCE AUTHENTICATION IS DEEMED UNNECESSARY
        /*val submit: ImageButton = binding.logOutButton
        submit.setOnClickListener {
            val returnToLogin = Intent(applicationContext, Login::class.java)
            startActivity(returnToLogin)
            finish()
        }*/

        val temperatureCard = binding.temperatureCard
        val humidityCard = binding.humidityCard
        val pressureCard = binding.pressureCard
        val rain24HrsCard = binding.rain24HrsCard
        val windSpeedCard = binding.windSpeedCard
        val soilTemperatureCard = binding.soilTemperatureCard
        val soilHumidityCard = binding.soilHumidityCard
        val location = binding.locationNameTextView

        temperatureCard.setOnClickListener(this)
        humidityCard.setOnClickListener(this)
        soilTemperatureCard.setOnClickListener(this)
        soilHumidityCard.setOnClickListener(this)
        pressureCard.setOnClickListener(this)
        rain24HrsCard.setOnClickListener(this)
        windSpeedCard.setOnClickListener(this)


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
        binding.textViewSoilTempValue.text = getString(R.string.soilTempValue, temperature)
        binding.textViewSoilHumidityValue.text = getString(R.string.soilHumidityValue, humidity)
        binding.textViewRain24hrValue.text = getString(R.string.rain24hrValue, rain)
        binding.textViewWindSpeedValue.text = getString(R.string.windSpeedValue, windspeed)
        binding.textViewWindDirValue.text = getString(R.string.windDirValue, windDir)
        binding.textViewPressureValue.text = getString(R.string.pressureValue, pressure)


        val randomNumberGeneratorButton: ImageButton = binding.menuButton
        randomNumberGeneratorButton.setOnClickListener {

            temperature = rand.nextInt(16).toString()
            humidity = rand.nextInt(16).toString()
            rain = rand.nextInt(16).toString()
            windspeed = rand.nextInt(16).toString()
            pressure = rand.nextInt(16).toString()

            binding.textViewTempValue.text = getString(R.string.tempValue, temperature)
            binding.textViewHumidityValue.text = getString(R.string.humidityValue, humidity)
            binding.textViewSoilTempValue.text = getString(R.string.soilTempValue, temperature)
            binding.textViewSoilHumidityValue.text = getString(R.string.soilHumidityValue, humidity)
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


    private fun testInsert() {
        val values = ContentValues().apply {
            put(StationsContract.Columns.STATION_NAME, "eui-23123123")
            put(StationsContract.Columns.STATION_LAT, "15")
            put(StationsContract.Columns.STATION_LON, "16")
            put(StationsContract.Columns.STATION_ALT, "12")
        }

        val uri = Uri.parse(StationsContract.CONTENT_URI.toString())
        val insertedUri = contentResolver.insert(uri, values)
        Log.d(TAG, "New row id (in uri) is $uri")
        Log.d(TAG, "id (in uri) is $insertedUri")
    }

    fun displayStations(jsonArray: JSONArray) {
        val entries = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val lat = jsonObject.get("lat")
            val lon = jsonObject.get("lon")
            entries.add(jsonObject.toString())
//            APIContract.getGeolocationData(lat.toString(), lon.toString()) { response ->
//                val json = JSONObject(response)
//                val features = json.getJSONArray("features")
//                val properties = features.getJSONObject(0).getJSONObject("properties")
//                val addressLine2 = properties.getString("address_line2")
//                Log.d(TAG,addressLine2)
//                entries.add(addressLine2)
//                }
            }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, entries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = binding.stationPicker
        spinner.adapter = adapter
    }


    override fun onClick(v: View) {
        when (v.id) {
            binding.temperatureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "temperature")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.humidityCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "humidity")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.soilTemperatureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "soiltemperature")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.soilHumidityCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "soilhumidity")
                startActivity(goToGraphActivity)
//              finish()
            }

            binding.pressureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "barometricpressure")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.rain24HrsCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "precipitation")
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


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}