package com.zxx.skyweather.data.model.place

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport

class County (@SerializedName("name") val countyName: String, @SerializedName("weather_id") val weatherId: String) : LitePalSupport() {
    @Transient val id = 0
    var cityId = 0
}
