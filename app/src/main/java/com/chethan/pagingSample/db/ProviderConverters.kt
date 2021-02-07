package com.chethan.pagingSample.db

import androidx.room.TypeConverter

/**
 * Created by Chethan on 5/4/2019.
 */


object ProviderConverters {
    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<Int>? {
        return data?.let {
            it.split(",").map {
                try {
                    it.toInt()
                } catch (ex: NumberFormatException) {

                    null
                }
            }
        }?.filterNotNull()
    }

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }
}
