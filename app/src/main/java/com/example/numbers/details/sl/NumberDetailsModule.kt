package com.example.numbers.details.sl

import com.example.numbers.details.presentation.NumberDetailsViewModel
import com.example.numbers.main.sl.Module
import com.example.numbers.main.sl.ProvideNumberDetails

class NumberDetailsModule(
    private val provideNumberDetails: ProvideNumberDetails
) : Module<NumberDetailsViewModel> {

    override fun viewModel() = NumberDetailsViewModel(provideNumberDetails.provideNumberDetails())
}