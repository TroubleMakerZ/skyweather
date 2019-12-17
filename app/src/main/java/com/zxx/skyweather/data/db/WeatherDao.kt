package com.zxx.skyweather.data.db

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.zxx.skyweather.SkyWeatherApplication
import com.google.gson.Gson
import com.zxx.skyweather.data.model.weather.Weather

class WeatherDao {

    fun cacheWeatherInfo(weather: Weather?) {
        if (weather == null) return
        PreferenceManager.getDefaultSharedPreferences(SkyWeatherApplication.context).edit {
            val weatherInfo = Gson().toJson(weather)
            putString("weather", weatherInfo)
        }
    }

    fun getCachedWeatherInfo(): Weather? {
        val weatherInfo = PreferenceManager.getDefaultSharedPreferences(SkyWeatherApplication.context).getString("weather", null)
        if (weatherInfo != null) {
            return Gson().fromJson(weatherInfo, Weather::class.java)
        }
        return null
    }

    fun cacheBingPic(bingPic: String?) {
        if (bingPic == null) return
        PreferenceManager.getDefaultSharedPreferences(SkyWeatherApplication.context).edit {
            putString("bing_pic", bingPic)
        }
    }

    fun getCachedBingPic(): String? = PreferenceManager.getDefaultSharedPreferences(SkyWeatherApplication.context).getString("bing_pic", null)

    private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        action(editor)
        editor.apply()
    }

}
