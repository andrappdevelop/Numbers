package com.example.numbers.numbers.data

import com.example.numbers.numbers.domain.HandleError
import com.example.numbers.numbers.domain.NoInternetConnectionException
import com.example.numbers.numbers.domain.ServiceUnavailableException
import java.net.UnknownHostException

class HandleDomainError : HandleError<Exception> {
    override fun handle(e: Exception): Exception = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}