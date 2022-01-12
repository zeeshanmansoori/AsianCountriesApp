package com.google.asiancountriesapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.asiancountriesapp.domain.model.Country
import com.google.asiancountriesapp.domain.repository.CountryRepository
import com.google.asiancountriesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: CountryRepository) :
    ViewModel() {

    private val _countries = MutableLiveData<Resource<List<Country>>>()
    val countries: LiveData<Resource<List<Country>>> get() = _countries

    init {

        setStateEvent(HomeStateEvent.GetCountryEvent)
    }

    fun setStateEvent(homeStateEvent: HomeStateEvent) {
        viewModelScope.launch {
            when (homeStateEvent) {
                is HomeStateEvent.GetCountryEvent -> {
                    repository.getAllCountries().onEach { resource ->
                        _countries.value = resource
                    }.launchIn(viewModelScope)
                }
                is HomeStateEvent.ClearDataBaseEvent -> clearDataBase()

            }
        }
    }

    private suspend fun clearDataBase() {
        repository.clearDatabase()
    }

}

sealed class HomeStateEvent {
    object GetCountryEvent : HomeStateEvent()
    object ClearDataBaseEvent : HomeStateEvent()
}