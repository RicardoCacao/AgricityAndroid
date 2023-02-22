package com.example.agricitytest2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 *
 * Basic database class for the application.
 *
 * The only class that should use this is [AppProvider].
 */

private const val TAG = "AppDatabase"

private const val DATABASE_NAME = "Agricity.db"
private const val DATABASE_VERSION = 3

internal class AppDatabase private constructor(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        Log.d(TAG, "AppDatabase: initialising")
    }

    override fun onCreate(db: SQLiteDatabase) {
        // CREATE TABLE STATIONS (_id INTEGER PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Description TEXT, SortOrder INTEGER);
        Log.d(TAG, "onCreate: starts")
        val sSQL = """CREATE TABLE ${StationsContract.TABLE_NAME} (
            ${StationsContract.Columns.ID} INTEGER PRIMARY KEY NOT NULL,
            ${StationsContract.Columns.STATION_NAME} TEXT NOT NULL,
            ${StationsContract.Columns.STATION_LAT} TEXT NOT NULL,
            ${StationsContract.Columns.STATION_LON} TEXT NOT NULL,
            ${StationsContract.Columns.STATION_ALT} TEXT NOT NULL,
            ${StationsContract.Columns.LOCATION} TEXT DEFAULT NULL);""".replaceIndent("")
        Log.d(TAG, sSQL)
        db.execSQL(sSQL)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade: starts")
        when(oldVersion) {
            1 -> {
                // upgrade logic from version 1
            }
            else -> throw IllegalStateException("onUpgrade() with unknown newVersion: $newVersion")
        }
    }

    companion object : SingletonHolder<AppDatabase, Context>(::AppDatabase)
//    companion object {
//
//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase =
//                instance ?: synchronized(this) {
//                    instance ?: AppDatabase(context).also { instance = it}
//                }
//    }
}
