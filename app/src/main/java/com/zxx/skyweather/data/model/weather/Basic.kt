package com.zxx.skyweather.data.model.weather

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.google.gson.annotations.SerializedName

class Basic {
    @SerializedName("city")
    var cityName = ""
    @SerializedName("id")
    var weatherId = ""
    lateinit var update: Update

    inner class Update {
        @SerializedName("loc")
        var updateTime = ""

        fun time() = updateTime.split(" ")[1]
    }
}
