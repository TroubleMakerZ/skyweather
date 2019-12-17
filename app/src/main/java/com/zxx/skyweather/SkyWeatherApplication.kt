package com.zxx.skyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.litepal.LitePal

class SkyWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}
