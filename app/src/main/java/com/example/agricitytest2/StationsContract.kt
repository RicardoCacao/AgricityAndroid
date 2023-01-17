package com.example.agricitytest2

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns


object StationsContract {

    internal const val TABLE_NAME = "Stations"

    /**
     * The URI to access the STATIONS table.
     */
    val CONTENT_URI: Uri = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)

    const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"

    // STATIONS fields
    object Columns {
        const val ID = BaseColumns._ID
        const val STATION_NAME = "Name"
        const val STATION_LAT = "Latitude"
        const val STATION_LON = "Longitude"
        const val STATION_ALT = "Altitude"
        const val STATION_VEG = "Vegetation"

    }

    fun getId(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id: Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }

}