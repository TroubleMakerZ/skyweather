package com.zxx.skyweather.data.model.weather

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.google.gson.annotations.SerializedName

class Now {
    @SerializedName("tmp")
    var temperature = ""
    @SerializedName("cond")
    lateinit var more: More

    fun degree() = "$temperature℃"

    inner class More {
        @SerializedName("txt")
        var info = ""
    }
}
