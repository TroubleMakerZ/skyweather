package com.zxx.skyweather.data.db

/**
 *Created by 11412 ON 2019/12/13.
// *
 */
object SkyWeatherDatabase {

    private var placeDao: PlaceDao? = null

    private var weatherDao: WeatherDao? = null

    fun getPlaceDao(): PlaceDao {
        if (placeDao == null) {
            placeDao = PlaceDao()
        }
        return placeDao!!
    }

    fun getWeatherDao(): WeatherDao {
        if (weatherDao == null) weatherDao = WeatherDao()
        return weatherDao!!
    }

}
