package com.google.asiancountriesapp.data.repository

import com.google.asiancountriesapp.data.model.CountryResponse.Companion.toDomainCountry
import com.google.asiancountriesapp.domain.model.Country
import com.google.asiancountriesapp.domain.repository.CountryRepository
import com.google.asiancountriesapp.domain.room.CountryDao
import com.google.asiancountriesapp.utils.Resource
import com.google.asiancountriesapp.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl(
    private val api: RetrofitCountryApi,
    private val countryDao: CountryDao
) : CountryRepository {

    override suspend fun getAllCountries(): Flow<Resource<List<Country>>> = flow {
        emit(Resource.Loading<List<Country>>())
        try {
            val data = api.getAllCountries().map {
                val data = it.toDomainCountry()
                countryDao.insert(data)
                data
            }

            log("api call ")
            emit(Resource.Success<List<Country>>(data = data))

        } catch (e: Exception) {
            val cachedData = countryDao.getAllCountries()
            emit(Resource.Error<List<Country>>(message = e, data = cachedData))
        }
    }

    override suspend fun clearDatabase() {
        countryDao.clearDatabase()
    }


}