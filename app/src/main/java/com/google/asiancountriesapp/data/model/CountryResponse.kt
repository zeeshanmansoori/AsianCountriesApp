package com.google.asiancountriesapp.data.model

import com.google.asiancountriesapp.domain.model.Country
import com.google.asiancountriesapp.domain.model.CountryName

data class CountryResponse(
    val borders: List<String>,
    val capital: List<String>,
    val currencies: Currencies,
    val flags: Flags,
    val languages: Languages,
    val name: Name,
    val region: String,
    val subregion: String
) {
    companion object {
        fun CountryResponse.toDomainCountry(): Country {
            return Country(
                id = name.official,
                name = CountryName(name.common, name.official),
                flag = flags.png,
                capital = capital.toString(),
                region = region,
                subRegion = subregion,
                borders = borders.toString(),
                languages = languages.toString()
            )
        }

    }
}