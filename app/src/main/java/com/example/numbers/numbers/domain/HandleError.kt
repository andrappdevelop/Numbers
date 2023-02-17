package com.example.numbers.numbers.domain

import com.example.numbers.R
import com.example.numbers.numbers.presentation.ManageResources

interface HandleError {

    fun handle(e: Exception): String

    class Base(private val manageResources: ManageResources) : HandleError {
        override fun handle(e: Exception) = manageResources.string(
            when (e) {
                is NoInternetConnectionException -> R.string.no_connection_message
                else -> R.string.service_is_unavailable
            }
        )
    }
}