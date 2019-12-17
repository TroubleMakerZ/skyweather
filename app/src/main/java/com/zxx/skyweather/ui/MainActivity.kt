package com.zxx.skyweather.ui

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zxx.skyweather.R
import com.zxx.skyweather.util.InjectorUtil
import com.zxx.skyweather.ui.weather.WeatherActivity
import android.content.Intent
import com.zxx.skyweather.ui.place.ChoosePlaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, InjectorUtil.getMainModelFactory()).get(MainViewModel::class.java)
        if (viewModel.isWeatherCached()) {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.container, ChoosePlaceFragment()).commit()
        }
    }

}
