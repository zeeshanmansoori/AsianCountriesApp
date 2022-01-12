package com.google.asiancountriesapp.domain.repository

import com.google.asiancountriesapp.domain.model.Country
import com.google.asiancountriesapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getAllCountries(): Flow<Resource<List<Country>>>
    suspend fun clearDatabase()
}