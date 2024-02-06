package com.fake.marketplace.data.source.locale.database.typeConverters

import androidx.room.TypeConverter
import com.fake.marketplace.data.source.locale.database.entities.product.InfoDbEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListInfoEntityConverter {
    private val gson = Gson()

    @TypeConverter
    fun restoreList(listOfString: String): List<InfoDbEntity> {
        return gson.fromJson(listOfString, object : TypeToken<List<InfoDbEntity>>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<InfoDbEntity>): String {
        return gson.toJson(listOfString)
    }

}