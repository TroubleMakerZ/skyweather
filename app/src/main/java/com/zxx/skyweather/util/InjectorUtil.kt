package com.zxx.skyweather.util

/**
 *Created by 11412 ON 2019/12/13.
 *
 */

import com.zxx.skyweather.data.PlaceRepository
import com.zxx.skyweather.data.WeatherRepository
import com.zxx.skyweather.data.db.SkyWeatherDatabase
import com.zxx.skyweather.data.network.SkyWeatherNetwork
import com.zxx.skyweather.ui.MainModelFactory
import com.zxx.skyweather.ui.place.ChoosePlaceModelFactory
import com.zxx.skyweather.ui.weather.WeatherModelFactory

object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance(SkyWeatherDatabase.getPlaceDao(), SkyWeatherNetwork.getInstance())

    private fun getWeatherRepository() = WeatherRepository.getInstance(SkyWeatherDatabase.getWeatherDao(), SkyWeatherNetwork.getInstance())

    fun getChoosePlaceModelFactory() = ChoosePlaceModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())
}
