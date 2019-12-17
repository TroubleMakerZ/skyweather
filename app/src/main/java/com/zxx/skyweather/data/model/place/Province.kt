package com.zxx.skyweather.data.model.place

/**
 *Created by 11412 ON 2019/12/13.
 *
 */

import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport

class Province (@SerializedName("name") val provinceName: String, @SerializedName("id") val provinceCode: Int) : LitePalSupport() {
    @Transient val id = 0
}
