package com.example.agricitytest2

import java.text.SimpleDateFormat

private const val TAG = "DateTimeToEpoch"


object DateTimeToEpoch {

    fun dateTimeToEpochLong(dateTime: String): Long {

        //Log.d(TAG, "Passed $dateTime")
        //Log.d(TAG, "Returned $df")
        return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTime)!!.time

    }
    fun dateTimeToEpochFloat(dateTime: String): Float {
        //Log.d(TAG, "Passed $dateTime")
        //Log.d(TAG, "Returned $df")
        return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTime)!!.time.toFloat()

    }



    //val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    //val data = df.parse(entrada.get("created_at").toString()).time.toFloat()
}