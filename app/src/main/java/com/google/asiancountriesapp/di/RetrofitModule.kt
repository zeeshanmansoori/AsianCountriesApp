package com.google.asiancountriesapp.di

import com.google.asiancountriesapp.data.repository.RetrofitCountryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(RetrofitCountryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideCountryRetrofit(builder: Retrofit.Builder): RetrofitCountryApi {
        return builder.build().create(RetrofitCountryApi::class.java)
    }


}