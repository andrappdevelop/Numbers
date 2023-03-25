package com.example.numbers.details.presentation

import androidx.lifecycle.ViewModel
import com.example.numbers.details.data.NumberFactDetails

class NumberDetailsViewModel(
    private val data: NumberFactDetails.Read
) : ViewModel(), NumberFactDetails.Read {

    override fun read() = data.read()
}