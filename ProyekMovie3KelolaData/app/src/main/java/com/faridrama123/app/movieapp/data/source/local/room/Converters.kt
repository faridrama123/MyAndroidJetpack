package com.faridrama123.app.data.source.local.room

import androidx.room.TypeConverter

//
object Converters {


    @TypeConverter
    @JvmStatic
    fun toList(data: String?): List<Int>? {
        return data?.let {
            it.split(",").map {
                it.toInt()
            }
        }?.filterNotNull()
    }

    @TypeConverter
    @JvmStatic
    fun fromList(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }

    @TypeConverter
    @JvmStatic
    fun toList2(data: String?): List<String>? {
        return data?.let {
            it.split(",").map {
                it
            }
        }?.filterNotNull()
    }

    @TypeConverter
    @JvmStatic
    fun fromList2(ints: List<String>?): String? {
        return ints?.joinToString(",")
    }




}