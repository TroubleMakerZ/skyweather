package com.zxx.skyweather.data.network.api

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.zxx.skyweather.data.model.place.City
import com.zxx.skyweather.data.model.place.County
import com.zxx.skyweather.data.model.place.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceService {

    @GET("api/china")
    fun getProvinces(): Call<MutableList<Province>>

    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId: Int): Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId: Int, @Path("cityId") cityId: Int): Call<MutableList<County>>

}
