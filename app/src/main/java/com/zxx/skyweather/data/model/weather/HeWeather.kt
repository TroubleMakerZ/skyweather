package com.zxx.skyweather.data.model.weather

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.google.gson.annotations.SerializedName

class HeWeather {

    @SerializedName("HeWeather")
    var weather: List<Weather>? = null

}
