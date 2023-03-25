package com.example.numbers.main.sl

import android.content.Context
import com.example.numbers.details.data.NumberFactDetails
import com.example.numbers.main.presentation.NavigationCommunication
import com.example.numbers.numbers.data.cache.CacheModule
import com.example.numbers.numbers.data.cloud.CloudModule
import com.example.numbers.numbers.presentation.DispatchersList
import com.example.numbers.numbers.presentation.ManageResources

interface Core : CloudModule, CacheModule, ManageResources, ProvideNavigation,
    ProvideNumberDetails {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        private val provideInstances: ProvideInstances
    ) : Core {

        private val numberDetails = NumberFactDetails.Base()

        private val navigationCommunication = NavigationCommunication.Base()

        private val manageResources: ManageResources = ManageResources.Base(context)

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }

        private val cloudModule by lazy {
            provideInstances.provideCloudModule()
        }

        private val cacheModule by lazy {
            provideInstances.provideCacheModule()
        }

        override fun <T> service(clasz: Class<T>): T = cloudModule.service(clasz)

        override fun provideDatabase() = cacheModule.provideDatabase()

        override fun string(id: Int) = manageResources.string(id)

        override fun provideNavigation() = navigationCommunication

        override fun provideNumberDetails() = numberDetails

        override fun provideDispatchers() = dispatchersList
    }
}

interface ProvideNavigation {
    fun provideNavigation(): NavigationCommunication.Mutable
}

interface ProvideNumberDetails {
    fun provideNumberDetails(): NumberFactDetails.Mutable
}