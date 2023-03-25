package com.example.numbers.main.sl

import com.example.numbers.main.presentation.MainViewModel

class MainModule(private val provideNavigation: ProvideNavigation) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(provideNavigation.provideNavigation())
}