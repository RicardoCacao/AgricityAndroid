package com.example.agricitytest2

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vishnusivadas.advanced_httpurlconnection.FetchData
import org.json.JSONArray


private const val TAG = "API"

object APIContract{

    fun getAPIData(parameter: String, station: Int): JSONArray{

        val url: String = "http://agricity.ipleiria.pt/api/$parameter/$station"
        Log.d(TAG, "O URL é $url")
        val fetchData: FetchData = FetchData(url)
        var resultado: JSONArray = JSONArray()
        if (fetchData.startFetch()) {
            Log.d(TAG, "Começou o fetch")
            if (fetchData.onComplete()) {
                resultado = JSONArray(fetchData.result)
                Log.d(TAG, resultado.toString())
                Log.d(TAG, "Completou o fetch")

            } else {
                Log.e(TAG, "Não Completou o fetch")
            }
        } else {
            Log.e(TAG, "Nao fez fetch")
        }
        Log.d(TAG, "Chegamos ao fim")

        return resultado
    }

}