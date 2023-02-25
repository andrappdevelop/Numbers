package com.example.numbers.numbers.data

import com.example.numbers.numbers.domain.NumberFact

class NumberDataToDomain : NumberData.Mapper<NumberFact> {
    override fun map(id: String, fact: String) = NumberFact(id, fact)
}