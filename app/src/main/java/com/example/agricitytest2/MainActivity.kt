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
import com.vishnusivadas.advanced_httpurlconnection.FetchData
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.jsonArray
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


        testInsert()

        //onStationRefresh()

        displayArrayEntries(APIContract.getStations())

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


//         CODE THAT RETURNED USER TO LOGIN SCREEN. NOW DEFUNCT SINCE AUTHENTICATION IS DEEMED UNNECESSARY
/*        val submit: ImageButton = binding.logOutButton
        submit.setOnClickListener {
            val returnToLogin = Intent(applicationContext, Login::class.java)
            startActivity(returnToLogin)
            finish()
        }*/


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

    fun onStationRefresh(){
//        runBlocking {
//            val json = APIContract.fetchJson("http://agricity.ipleiria.pt/api/stations").await()
//            // Use the json object here
//            Log.d(TAG, json.toString())
//            val jeff: JSONArray = json.getAsJsonArray
//            Log.d(TAG, jeff.toString())
//        }
    }




    fun displayArrayEntries(jsonArray: JSONArray) {


        val entries = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            entries.add(jsonArray.getString(i))
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, entries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = binding.stationPicker
        spinner.adapter = adapter
        val countTextView = binding.numero
        "Number of entries: ${jsonArray.length()}".also { countTextView.text = it }


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

}