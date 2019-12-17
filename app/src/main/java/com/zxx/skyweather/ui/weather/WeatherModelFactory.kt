package com.zxx.skyweather.ui.weather

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zxx.skyweather.data.WeatherRepository

class WeatherModelFactory(private val repository: WeatherRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }
}
