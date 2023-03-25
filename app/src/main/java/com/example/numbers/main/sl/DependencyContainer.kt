package com.example.numbers.main.sl

import androidx.lifecycle.ViewModel
import com.example.numbers.details.presentation.NumberDetailsViewModel
import com.example.numbers.details.sl.NumberDetailsModule
import com.example.numbers.main.presentation.MainViewModel
import com.example.numbers.numbers.presentation.NumbersViewModel
import com.example.numbers.numbers.sl.NumbersModule

interface DependencyContainer {

    fun <T : ViewModel> module(clasz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> {
            throw IllegalStateException("no module found for $clasz")
        }
    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ) : DependencyContainer {

        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> = when (clasz) {
            MainViewModel::class.java -> MainModule(core)
            NumbersViewModel.Base::class.java -> NumbersModule(core)
            NumberDetailsViewModel::class.java -> NumberDetailsModule(core)
            else -> dependencyContainer.module(clasz)
        }
    }
}