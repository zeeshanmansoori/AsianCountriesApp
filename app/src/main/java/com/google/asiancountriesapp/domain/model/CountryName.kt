package com.google.asiancountriesapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryName(
    val commonName: String,
    val officialName: String
) : Parcelable