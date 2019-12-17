package com.zxx.skyweather.data.db

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.zxx.skyweather.data.model.place.City
import com.zxx.skyweather.data.model.place.County
import com.zxx.skyweather.data.model.place.Province
import org.litepal.LitePal

class PlaceDao {

    fun getProvinceList(): MutableList<Province> = LitePal.findAll(Province::class.java)

    fun getCityList(provinceId: Int): MutableList<City> = LitePal.where("provinceId = ?", provinceId.toString()).find(City::class.java)

    fun getCountyList(cityId: Int): MutableList<County> = LitePal.where("cityId = ?", cityId.toString()).find(County::class.java)

    fun saveProvinceList(provinceList: List<Province>?) {
        if (provinceList != null && provinceList.isNotEmpty()) {
            LitePal.saveAll(provinceList)
        }
    }

    fun saveCityList(cityList: List<City>?) {
        if (cityList != null && cityList.isNotEmpty()) {
            LitePal.saveAll(cityList)
        }
    }

    fun saveCountyList(countyList: List<County>?) {
        if (countyList != null && countyList.isNotEmpty()) {
            LitePal.saveAll(countyList)
        }
    }

}
