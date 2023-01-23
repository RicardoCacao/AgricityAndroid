package com.example.agricitytest2

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.vishnusivadas.advanced_httpurlconnection.FetchData
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.json.JSONObject


private const val TAG = "API"

object APIContract {

    fun getAPIData(parameter: String, station: Int): JSONArray {

        val url: String = "http://agricity.ipleiria.pt/api/$parameter/$station"
        Log.d(TAG, "O URL é $url")
        val fetchData: FetchData = FetchData(url)
        var resultado: JSONArray = JSONArray()
        if (fetchData.startFetch()) {
            Log.d(TAG, "Começou o fetch")
            if (fetchData.onComplete()) {
                try {
                    resultado = JSONArray(fetchData.result)
                    Log.d(TAG, resultado.toString())
                    Log.d(TAG, "Completou o fetch")
                } catch (e: Exception) {
                    Log.e(TAG, "Exception ocurred: $e")
                    resultado = JSONArray()
                }

            } else {
                Log.e(TAG, "Não Completou o fetch")
            }
        } else {
            Log.e(TAG, "Nao fez fetch")
        }
        Log.d(TAG, "Chegamos ao fim")

        return resultado
    }

    fun fetchJson(url: String): Deferred<JsonElement> = GlobalScope.async {
        val jsonString = URL(url).readText()
        Json.parseToJsonElement(jsonString)
    }





    fun getStations(): JSONArray {
        val url: String = "http://agricity.ipleiria.pt/api/stations"
        Log.d(TAG, "Fetching Stations")


//        val connection = url.openConnection() as HttpURLConnection
//        connection.requestMethod = "POST"
//        connection.doOutput = true
//        connection.doInput = true
//        connection.useCaches = false
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
//        val data = ""
//        val outputStream = connection.outputStream
//        outputStream.write(data.toByteArray())
//        outputStream.flush()
//        outputStream.close()
//        val responseCode = connection.responseCode
//        val responseMessage = connection.responseMessage
//
//        Log.d(TAG, responseCode.toString() + "\n")
//        Log.d(TAG, responseMessage)
//        return JSONArray(responseMessage)


        val fetchData: FetchData = FetchData(url)
        var resultado: JSONArray = JSONArray()

        if (!fetchData.startFetch()) {
            Log.e(TAG, "Error starting fetch")
            return resultado
        }
        if (!fetchData.onComplete()){
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