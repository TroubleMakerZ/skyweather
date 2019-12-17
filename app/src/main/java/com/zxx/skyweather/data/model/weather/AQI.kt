package com.zxx.skyweather.data.model.weather

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
class AQI {
    lateinit var city: AQICity

    inner class AQICity {
        var aqi = ""
        var pm25  = ""
    }
}
