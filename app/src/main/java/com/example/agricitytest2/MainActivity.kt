package com.example.agricitytest2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject


private const val TAG = "MAIN ACTIVITY"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        //testInsert()
        //onStationRefresh()


        /*CODE THAT RETURNED USER TO LOGIN SCREEN. NOW DEFUNCT SINCE AUTHENTICATION IS DEEMED UNNECESSARY*/
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
        val stationPicker = binding.stationPicker

        displayStations(APIContract.getStations(this))
        //Sets the location view text
        try {
            val stationId: Int = stationPicker.selectedItem.toString().toInt()
            setLocationTextNameValue(stationId.toLong())
        } catch (e: Exception) {
            Log.e(TAG, "Exception while assigning ID: ${e.message}")
        }

        temperatureCard.setOnClickListener(this)
        humidityCard.setOnClickListener(this)
        soilTemperatureCard.setOnClickListener(this)
        soilHumidityCard.setOnClickListener(this)
        pressureCard.setOnClickListener(this)
        rain24HrsCard.setOnClickListener(this)
        windSpeedCard.setOnClickListener(this)
        location.setOnClickListener(this)



        stationPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            @SuppressLint("ResourceType")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val stationId = stationPicker.selectedItem.toString()
                setLocationTextNameValue(stationId.toLong())

                val mapaDeValores = APIContract.getAllDataFromStation(
                    parent?.getItemAtPosition(position).toString().toInt()
                )

                if (mapaDeValores.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Demasiados pedidos. Tente novamente mais tarde",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (mapaDeValores.size == 9) {
                        binding.textViewTempValue.text =
                            getString(R.string.tempValue, mapaDeValores["temperature"])
                        binding.textViewHumidityValue.text =
                            getString(R.string.humidityValue, mapaDeValores["humidity"])
                        binding.textViewSoilTempValue.text =
                            getString(R.string.soilTempValue, mapaDeValores["soiltemperature"])
                        binding.textViewSoilHumidityValue.text =
                            getString(R.string.soilHumidityValue, mapaDeValores["soilhumidity"])
                        binding.textViewRain24hrValue.text =
                            getString(R.string.rain24hrValue, mapaDeValores["precipitation"])
                        binding.textViewWindSpeedValue.text =
                            getString(R.string.windSpeedValue, mapaDeValores["windspeed"])
                        binding.textViewPressureValue.text =
                            getString(
                                R.string.pressureValue,
                                mapaDeValores["barometricpressure"]
                            )
                        binding.textViewWindDirValue.text = getString(
                            R.string.windDirValue,
                            mapaDeValores["winddirection"]?.substringBefore("\\")
                                ?.replace("\"", "")
                        )
                    }

                }
            }

        }

    }


    override fun onClick(v: View) {
        val stationId: Int = binding.stationPicker.selectedItem.toString().toInt()
        Log.d(TAG, "O valor é $stationId")
        when (v.id) {
            binding.temperatureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "temperature")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.humidityCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "humidity")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.soilTemperatureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "soiltemperature")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.soilHumidityCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "soilhumidity")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
//              finish()
            }

            binding.pressureCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "barometricpressure")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.rain24HrsCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "precipitation")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.windSpeedCard.id -> {
                val goToGraphActivity = Intent(applicationContext, GraphActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "windspeed")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
//              finish()
            }
            binding.locationNameTextView.id -> {
                val goToGraphActivity =
                    Intent(applicationContext, MapsMarkerActivity::class.java)
                goToGraphActivity.putExtra("Parameter", "windspeed")
                goToGraphActivity.putExtra("Station", "$stationId")
                startActivity(goToGraphActivity)
            }
        }

    }


    override fun onResume() {
        super.onResume()
        val stationId: Int = binding.stationPicker.selectedItem.toString().toInt()
        APIContract.getAllDataFromStation(stationId)
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


/*    private fun testInsert() {
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
    }*/


    fun displayStations(jsonArray: JSONArray) {
        val entries = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
//            val lat = jsonObject.get("lat")
//            val lon = jsonObject.get("lon")
            val id = jsonObject.get("id")

            entries.add(id.toString())


        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, entries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = binding.stationPicker
        spinner.adapter = adapter
    }


    fun setLocationTextNameValue(id: Long) {

        val selectStation = StationsContract.Columns.ID + " = ?"
        val selectionArgs = arrayOf(id.toString())

        Log.d(TAG, "Selection is $selectStation and selection arguments are $selectionArgs")
        try {
            val existe = StationsContract.checkIfRowExists(contentResolver, id)
            Log.d(TAG, "Existe ou nao: $existe")
            if (existe) {
                val cursor = contentResolver.query(
                    StationsContract.buildUriFromId(id), null, null, null, null
                )
                val columnIndex = cursor?.getColumnIndex(StationsContract.Columns.LOCATION)
                if (columnIndex != null && columnIndex > 0) {
                    if (cursor.moveToFirst()) {
                        if (!(cursor.getString(columnIndex).isNullOrEmpty())) {
                            val locationValue = binding.locationNameTextView
                            locationValue.text = getString(
                                R.string.locationNameTextViewValue,
                                cursor.getString(columnIndex)
                            )
                            Log.d(TAG, "Definicao de LocationNameTextViewValue")
                        } else {

                            var lat: String = cursor.getColumnIndex(StationsContract.Columns.STATION_LAT).toString()
                            lat = cursor.getString(lat.toInt())
                            var lon: String = cursor.getColumnIndex(StationsContract.Columns.STATION_LON).toString()
                            lon = cursor.getString(lon.toInt())
                            APIContract.getGeolocationData(lat, lon) { response ->
                                val json = JSONObject(response)
                                val features = json.getJSONArray("features")
                                val properties =
                                    features.getJSONObject(0).getJSONObject("properties")
                                val addressLine2 = properties.getString("address_line2")
                                Log.d(TAG, addressLine2)
                                val values = ContentValues().apply {
                                    put(
                                        StationsContract.Columns.LOCATION,
                                        addressLine2
                                    )
                                }
                                val locationValue = binding.locationNameTextView
                                locationValue.text = getString(
                                    R.string.locationNameTextViewValue,addressLine2
                                )

                                Log.d(TAG, "Insertions de LocationNameTextViewValue no base de dados")
                                val taskUri = StationsContract.buildUriFromId(id)
                                val rowsAffected =
                                    contentResolver.update(taskUri, values, null, null)
                                Log.d(TAG, "Number of rows updated is $rowsAffected")

                            }

                        }
                    }
                }
                cursor?.close()

            }
        } catch (e: Exception) {
            Log.e(
                TAG,
                "Exception Caught while trying to query Database for location value: ${e.message}"
            )
        }

    }


}