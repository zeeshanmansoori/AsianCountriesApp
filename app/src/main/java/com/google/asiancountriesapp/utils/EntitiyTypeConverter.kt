package com.google.asiancountriesapp.utils

import androidx.room.TypeConverter
import com.google.asiancountriesapp.domain.model.CountryName
import com.google.gson.Gson

class EntitiyTypeConverter {

    @TypeConverter
    fun countryNameToJson(value: CountryName): String? = Gson().toJson(value)

    @TypeConverter
    fun jsonToCountryName(value: String?) = Gson().fromJson(value, CountryName::class.java)

}