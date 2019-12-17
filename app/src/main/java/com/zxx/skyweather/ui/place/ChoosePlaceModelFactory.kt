package com.zxx.skyweather.ui.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zxx.skyweather.data.PlaceRepository

class ChoosePlaceModelFactory(private val repository: PlaceRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChoosePlaceViewModel(repository) as T
    }
}
