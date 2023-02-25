package com.example.numbers.main.sl

import androidx.lifecycle.ViewModel
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
        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> =
            if (clasz == NumbersViewModel::class.java)
                NumbersModule(core)
            else
                dependencyContainer.module(clasz)
    }
}