package com.zxx.skyweather.ui

import androidx.lifecycle.ViewModel
import com.zxx.skyweather.data.WeatherRepository

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    fun isWeatherCached() = repository.isWeatherCached()

}
