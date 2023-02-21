package com.example.numbers.main

import android.app.Application
import com.example.numbers.BuildConfig
import com.example.numbers.numbers.data.cloud.CloudModule


class NumbersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val cloudModule = if (BuildConfig.DEBUG) CloudModule.Debug() else CloudModule.Release()
    }
}