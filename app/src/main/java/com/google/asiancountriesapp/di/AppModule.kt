package com.google.asiancountriesapp.di

import com.google.asiancountriesapp.data.repository.CountryRepositoryImpl
import com.google.asiancountriesapp.data.repository.RetrofitCountryApi
import com.google.asiancountriesapp.domain.repository.CountryRepository
import com.google.asiancountriesapp.domain.room.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountryRepository(
        api: RetrofitCountryApi,
        dao: CountryDao
    ): CountryRepository {
        return CountryRepositoryImpl(api, dao)
    }
}