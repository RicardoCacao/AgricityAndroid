package com.example.agricitytest2

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import android.content.Context
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import com.vishnusivadas.advanced_httpurlconnection.FetchData
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.*
import okhttp3.*
import okio.IOException
import org.json.JSONObject


private const val TAG = "API Contract"

object APIContract {

    fun getAPIData(parameter: String, station: Int): JSONArray {

        val url: String = "http://agricity.ipleiria.pt/api/$parameter/$station"
        Log.d(TAG, "O URL é $url")

        val fetchData: FetchData = FetchData(url)
        var resultado: JSONArray = JSONArray()

        if (!fetchData.startFetch()) {
            Log.e(TAG, "Error starting fetch")
            return resultado
        }
        if (!fetchData.onComplete()) {
            Log.e(TAG, "Error completing fetch")
            return resultado
        }

        return try {
            resultado = JSONArray(fetchData.result)
            Log.d(TAG, resultado.toString())
            Log.d(TAG, "Completou o fetch")
            resultado
        } catch (e: Exception) {
            Log.e(TAG, "Exception ocurred: $e")
            resultado
        }
    }

    fun fetchJson(url: String): Deferred<JsonElement> = GlobalScope.async {
        val jsonString = URL(url).readText()
        Json.parseToJsonElement(jsonString)
    }

/*    fun getGeolocationData(lat: String, lon: String): String?{
        val client = OkHttpClient()
        val request = Request.Builder().url("https://maps.googleapis.com/maps/api/geocode/json?latlng=${lat},${lon}&key=AIzaSyBXGTirM254qD9mMv6FxYRyFRIML_LoiLg").build()
        Log.d(TAG, request.toString())
        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }*/

    fun getGeolocationData(lat: String, lon: String, callback: (String) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.geoapify.com/v1/geocode/reverse?lat=${lat}&lon=${lon}&apiKey=9e7b172e21ae4bb2b272d8de05388ec6")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback("")
            }

            override fun onResponse(call: Call, response: Response) {
                callback(response.body?.string() ?: "")
            }
        })
    }


    fun getAllDataFromStation(station: Int): MutableMap<String, String> {
        var resultado = mutableMapOf<String, String>()
        val listOfParameters = arrayOf(
            "temperature",
            "humidity",
            "barometricpressure",
            "precipitation",
            "soilhumidity",
            "soiltemperature",
            "sunlightuvi",
            "winddirection",
            "windspeed"
        )
        try {

            for (parameter in listOfParameters) {
                val url: String = "http://agricity.ipleiria.pt/api/$parameter/$station"
                var json: JSONArray = JSONArray()
                Log.d(TAG, "O URL é $url")

                val fetchData: FetchData = FetchData(url)


                if (!fetchData.startFetch()) {
                    Log.e(TAG, "Error starting fetch")
                    break
                }
                if (!fetchData.onComplete()) {
                    Log.e(TAG, "Error completing fetch with $parameter")
                    break
                }
                json = JSONArray(fetchData.result)

                Log.d(TAG, json.toString())
                if (json != JSONArray()) {
                    val jsonArray = Json.decodeFromString<JsonArray>(json.toString())
                    val lastJsonObject = jsonArray.lastOrNull() as? JsonObject
                    val lastValor = lastJsonObject?.get("valor")?.jsonPrimitive?.toString()

                    resultado[parameter] = lastValor.toString()
                }
                //Log.d(TAG, "Completou o fetch")
                //Log.d(TAG, resultado.toString())

            }
        } catch (e: Exception) {
            println("An error occurred while processing parameters from station $station: ${e.message}")
        }
        Log.d(TAG, "All latest values from station $station: $resultado")
        return resultado
    }


    fun getStations(): JSONArray {
        val url: String = "http://agricity.ipleiria.pt/api/stations"
        Log.d(TAG, "Fetching Stations")

        val fetchData: FetchData = FetchData(url)
        var resultado: JSONArray = JSONArray()

        if (!fetchData.startFetch()) {
            Log.e(TAG, "Error starting fetch")
            return resultado
        }
        if (!fetchData.onComplete()) {
            Log.e(TAG, "Error completing fetch")
        }

        try {
            resultado = JSONArray(fetchData.result)
            Log.d(TAG, resultado.toString())
            Log.d(TAG, "Completou o fetch")
            return resultado
        } catch (e: Exception) {
            Log.e(TAG, "Exception ocurred: $e")
            return resultado
        }

    }

}