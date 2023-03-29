package com.example.numbers.main.presentation

import com.example.numbers.details.presentation.NumberDetailsFragment
import com.example.numbers.numbers.presentation.NumbersFragment

sealed class Screen {

    abstract fun fragment(): Class<out BaseFragment<*>>

    object Details : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumberDetailsFragment::class.java
    }

    object Numbers : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumbersFragment::class.java
    }
}