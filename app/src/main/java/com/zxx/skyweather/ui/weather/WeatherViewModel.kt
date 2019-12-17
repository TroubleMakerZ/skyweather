package com.zxx.skyweather.ui.weather

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxx.skyweather.SkyWeatherApplication
import com.zxx.skyweather.data.WeatherRepository
import com.zxx.skyweather.data.model.weather.Weather
import kotlinx.coroutines.launch

/**
 *Created by 11412 ON 2019/12/13.
 *
 */


class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    var weather = MutableLiveData<Weather>()

    var bingPicUrl = MutableLiveData<String>()

    var refreshing = MutableLiveData<Boolean>()

    var weatherInitialized = MutableLiveData<Boolean>()

    var weatherId = ""

    fun getWeather() {
        launch ({
            weather.value = repository.getWeather(weatherId)
            weatherInitialized.value = true
        }, {
            Toast.makeText(SkyWeatherApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })
        getBingPic(false)
    }

    fun refreshWeather() {
        refreshing.value = true
        launch ({
            weather.value = repository.refreshWeather(weatherId)
            refreshing.value = false
            weatherInitialized.value = true
        }, {
            Toast.makeText(SkyWeatherApplication.context, it.message, Toast.LENGTH_SHORT).show()
            refreshing.value = false
        })
        getBingPic(true)
    }

    fun isWeatherCached() = repository.isWeatherCached()

    fun getCachedWeather() = repository.getCachedWeather()

    fun onRefresh() {
        refreshWeather()
    }

    private fun getBingPic(refresh: Boolean) {
        launch({
            bingPicUrl.value = if (refresh) repository.refreshBingPic() else repository.getBingPic()
        }, {
            Toast.makeText(SkyWeatherApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        }
    }

}
