package com.example.numbers.numbers.data

interface NumbersCacheDataSource : FetchNumber {

    suspend fun contains(number: String): Boolean

    suspend fun allNumbers(): List<NumberData>

    suspend fun saveNumber(numberData: NumberData)
}

interface FetchNumber {
    suspend fun number(number: String): NumberData
}