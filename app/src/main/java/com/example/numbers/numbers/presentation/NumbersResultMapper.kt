package com.example.numbers.numbers.presentation

import com.example.numbers.numbers.domain.NumberFact
import com.example.numbers.numbers.domain.NumbersResult

class NumbersResultMapper(
    private val communications: NumbersCommunications,
    private val mapper: NumberFact.Mapper<NumberUi>
) : NumbersResult.Mapper<Unit> {

    override fun map(list: List<NumberFact>, errorMessage: String) = communications.showState(
        if (errorMessage.isEmpty()) {
            val numbersList = list.map { it.map(mapper) }
            if (numbersList.isNotEmpty())
                communications.showList(numbersList)
            UiState.Success()
        } else
            UiState.Error(errorMessage)
    )
}