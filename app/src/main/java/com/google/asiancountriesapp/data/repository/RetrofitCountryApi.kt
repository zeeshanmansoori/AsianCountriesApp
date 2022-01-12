package com.google.asiancountriesapp.data.repository

import com.google.asiancountriesapp.data.model.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitCountryApi {

    companion object {
        const val BASE_URL = "https://restcountries.com/v3.1/region/"
    }

    @GET("asia")
    suspend fun getAllCountries(@Query("fields") fields: String = "name,capital,currencies,flags,capital,region,subregion,borders,languages")
            : List<CountryResponse>
}

