package com.google.asiancountriesapp.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.google.asiancountriesapp.domain.model.Country
import com.google.asiancountriesapp.utils.EntitiyTypeConverter

@Database(entities = [Country::class], version = 1, exportSchema = false)
@TypeConverters(EntitiyTypeConverter::class)
abstract class CountryDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "AsianCountriesDb"
    }

    abstract fun countryDao(): CountryDao
}