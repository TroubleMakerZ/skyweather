package com.zxx.skyweather.ui.place

/**
 *Created by 11412 ON 2019/12/13.
 *
 */
import com.zxx.skyweather.ui.place.ChoosePlaceFragment.Companion.LEVEL_CITY
import com.zxx.skyweather.ui.place.ChoosePlaceFragment.Companion.LEVEL_COUNTY
import com.zxx.skyweather.ui.place.ChoosePlaceFragment.Companion.LEVEL_PROVINCE
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxx.skyweather.SkyWeatherApplication
import com.zxx.skyweather.data.PlaceRepository
import com.zxx.skyweather.data.model.place.City
import com.zxx.skyweather.data.model.place.County
import com.zxx.skyweather.data.model.place.Province

import kotlinx.coroutines.launch
import java.util.*

class ChoosePlaceViewModel(private val repository: PlaceRepository) : ViewModel() {

    var currentLevel = MutableLiveData<Int>()

    var dataChanged = MutableLiveData<Int>()

    var isLoading = MutableLiveData<Boolean>()

    var placeSelected = MutableLiveData<Boolean>()

    var selectedProvince: Province? = null

    var selectedCity: City? = null

    var selectedCounty: County? = null

    lateinit var provinces: MutableList<Province>

    lateinit var cities: MutableList<City>

    lateinit var counties: MutableList<County>

    val dataList = ArrayList<String>()

    fun getProvinces() {
        currentLevel.value = LEVEL_PROVINCE
        launch {
            provinces = repository.getProvinceList()
            dataList.addAll(provinces.map { it.provinceName })
        }
    }

    private fun getCities() = selectedProvince?.let {
        currentLevel.value = LEVEL_CITY
        launch {
            cities = repository.getCityList(it.provinceCode)
            dataList.addAll(cities.map { it.cityName })
        }
    }

    private fun getCounties() = selectedCity?.let {
        currentLevel.value = LEVEL_COUNTY
        launch {
            counties = repository.getCountyList(it.provinceId, it.cityCode)
            dataList.addAll(counties.map { it.countyName })
        }
    }

    fun onListViewItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when {
            currentLevel.value == LEVEL_PROVINCE -> {
                selectedProvince = provinces[position]
                getCities()
            }
            currentLevel.value == LEVEL_CITY -> {
                selectedCity = cities[position]
                getCounties()
            }
            currentLevel.value == LEVEL_COUNTY -> {
                selectedCounty = counties[position]
                placeSelected.value = true
            }
        }
    }

    fun onBack() {
        if (currentLevel.value == LEVEL_COUNTY) {
            getCities()
        } else if (currentLevel.value == LEVEL_CITY) {
            getProvinces()
        }
    }

    private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            Toast.makeText(SkyWeatherApplication.context, t.message, Toast.LENGTH_SHORT).show()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }
    }

}
