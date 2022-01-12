package com.google.asiancountriesapp.domain.room

import androidx.room.*
import com.google.asiancountriesapp.domain.model.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: Country): Long

    @Query("select * from countries")
    suspend fun getAllCountries(): List<Country>

    @Query("delete from countries")
    suspend fun clearDatabase()


}