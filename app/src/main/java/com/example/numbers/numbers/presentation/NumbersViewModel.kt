package com.example.numbers.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbers.numbers.domain.NumbersInteractor
import com.example.numbers.numbers.domain.NumbersResult
import kotlinx.coroutines.launch

class NumbersViewModel(
    private val communications: NumbersCommunications,
    private val interactor: NumbersInteractor,
    private val numbersResultMapper: NumbersResult.Mapper<Unit>
) : ObserveNumbers, FetchNumbers, ViewModel() {

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) =
        communications.observeProgress(owner, observer)

    override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
        communications.observeState(owner, observer)

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) =
        communications.observeList(owner, observer)

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            communications.showProgress(true)
            viewModelScope.launch {
                val result = interactor.init()
                communications.showProgress(false)
                result.map(numbersResultMapper)
            }
        }
    }

    override fun fetchRandomNumberFact() {
        TODO("Not yet implemented")
    }

    override fun fetchNumberFact(number: String) {
        TODO("Not yet implemented")
    }

}

interface FetchNumbers {

    fun init(isFirstRun: Boolean)

    fun fetchRandomNumberFact()

    fun fetchNumberFact(number: String)
}