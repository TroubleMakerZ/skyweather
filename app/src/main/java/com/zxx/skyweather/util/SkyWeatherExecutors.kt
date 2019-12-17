package com.zxx.skyweather.util

/**
 *Created by 11412 ON 2019/12/13.
 *
 */

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object SkyWeatherExecutors {

    val diskIO = Executors.newSingleThreadExecutor()

    val networkIO = Executors.newFixedThreadPool(3)

    val mainThread = MainThreadExecutor()

    class MainThreadExecutor : Executor {
        val handler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable?) {
            handler.post(command)
        }
    }

}
