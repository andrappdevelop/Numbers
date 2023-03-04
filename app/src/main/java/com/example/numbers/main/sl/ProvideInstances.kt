package com.example.numbers.main.sl

import android.content.Context
import com.example.numbers.numbers.data.cache.CacheModule
import com.example.numbers.numbers.data.cloud.CloudModule

interface ProvideInstances {

    fun provideCloudModule(): CloudModule
    fun provideCacheModule(): CacheModule

    class Release(private val context: Context) : ProvideInstances {
        override fun provideCloudModule() = CloudModule.Base()
        override fun provideCacheModule() = CacheModule.Base(context)
    }

    class Mock(private val context: Context) : ProvideInstances {
        override fun provideCloudModule() = CloudModule.Mock()
        override fun provideCacheModule() = CacheModule.Mock(context)
    }
}