package com.zxx.skyweather.data.model.weather

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.google.gson.annotations.SerializedName

class Weather {
    var status = ""
    lateinit var basic: Basic
    lateinit var aqi: AQI
    lateinit var now: Now
    lateinit var suggestion: Suggestion
    @SerializedName("daily_forecast")
    lateinit var forecastList: List<Forecast>
}
