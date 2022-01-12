package com.google.asiancountriesapp.di

import android.content.Context
import androidx.room.Room
import com.google.asiancountriesapp.domain.room.CountryDao
import com.google.asiancountriesapp.domain.room.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideCountryDb(@ApplicationContext context: Context): CountryDatabase {
        return Room.databaseBuilder(
            context,
            CountryDatabase::class.java,
            CountryDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryDao(database: CountryDatabase): CountryDao {
        return database.countryDao()
    }

}