package com.fake.marketplace.data.source.locale.database.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListStringConverter {
    private val gson = Gson()

    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return gson.fromJson(listOfString, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return gson.toJson(listOfString)
    }

}