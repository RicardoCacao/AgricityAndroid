package com.example.agricitytest2

import android.util.Log
import java.text.SimpleDateFormat

private const val TAG = "DateTimeToEpoch"


object DateTimeToEpoch {

    fun dateTimeToEpochLong(dateTime: String): Long {

        //Log.d(TAG, "Passed $dateTime")
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime.toString()).time.toLong()
        //Log.d(TAG, "Returned $df")
        return  df

    }
    fun dateTimeToEpochFloat(dateTime: String): Float {
        //Log.d(TAG, "Passed $dateTime")
        val df =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime.toString()).time.toFloat()
        //Log.d(TAG, "Returned $df")
        return  df

    }



    //val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    //val data = df.parse(entrada.get("created_at").toString()).time.toFloat()
}