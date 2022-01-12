package com.google.asiancountriesapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.asiancountriesapp.utils.EntitiyTypeConverter
import kotlinx.parcelize.Parcelize


@Entity(tableName = "countries")
@Parcelize
data class Country(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: CountryName,
    val flag: String?,
    val capital: String,
    val region: String,
    val subRegion: String,
    val borders: String,
    val languages: String,
):Parcelable