package com.example.numbers.numbers.sl

import com.example.numbers.main.sl.Core
import com.example.numbers.main.sl.Module
import com.example.numbers.numbers.data.*
import com.example.numbers.numbers.data.cache.NumberDataToCache
import com.example.numbers.numbers.data.cache.NumbersCacheDataSource
import com.example.numbers.numbers.data.cloud.NumbersCloudDataSource
import com.example.numbers.numbers.data.cloud.NumbersService
import com.example.numbers.numbers.domain.*
import com.example.numbers.numbers.presentation.*

class NumbersModule(private val core: Core) : Module<NumbersViewModel.Base> {

    override fun viewModel(): NumbersViewModel.Base {
        val communications = NumbersCommunications.Base(
            ProgressCommunication.Base(),
            NumberStateCommunication.Base(),
            NumbersListCommunication.Base()
        )
        val cacheDataSource = NumbersCacheDataSource.Base(
            core.provideDatabase().numbersDao(),
            NumberDataToCache()
        )
        val repository = BaseNumbersRepository(
            NumbersCloudDataSource.Base(core.service(NumbersService::class.java)),
            cacheDataSource,
            HandleDataRequest.Base(
                cacheDataSource,
                NumberDataToDomain(),
                HandleDomainError()
            ),
            NumberDataToDomain()
        )
        return NumbersViewModel.Base(
            HandleNumbersRequest.Base(
                core.provideDispatchers(),
                communications,
                NumbersResultMapper(communications, NumberUiMapper())
            ),
            core,
            communications,
            NumbersInteractor.Base(
                repository,
                HandleRequest.Base(
                    HandleError.Base(core),
                    repository
                ),
                core.provideNumberDetails()
            ),
            core.provideNavigation(),
            DetailsUi()
        )
    }
}