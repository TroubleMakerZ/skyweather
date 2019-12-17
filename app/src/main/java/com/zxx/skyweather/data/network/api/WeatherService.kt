package com.zxx.skyweather.data.network.api

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.zxx.skyweather.data.model.weather.HeWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("api/weather")
    fun getWeather(@Query("cityid") weatherId: String): Call<HeWeather>

    @GET("api/bing_pic")
    fun getBingPic(): Call<String>

}
