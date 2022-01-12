package com.google.asiancountriesapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.asiancountriesapp.domain.model.Country
import com.google.asiancountriesapp.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(state: SavedStateHandle) : ViewModel() {

    private val _data = MutableLiveData<Country>()
    val data: LiveData<Country> get() = _data

    init {

        val country = state.get<Country?>("country")
        log("DetailViewModel c = $country")
        if (country != null)
            _data.value = country!!
    }
}