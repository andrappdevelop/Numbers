package com.example.numbers.numbers.domain

import com.example.numbers.numbers.presentation.NumberUi

class NumberUiMapper : NumberFact.Mapper<NumberUi> {
    override fun map(id: String, fact: String): NumberUi = NumberUi(id, fact)
}