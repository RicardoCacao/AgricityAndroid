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
        const val STATION_NAME = "nomeEstacao"
        const val STATION_LAT = "lat"
        const val STATION_LON = "lon"
        const val STATION_ALT = "altitude"
    }

    fun getId(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id: Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }

}