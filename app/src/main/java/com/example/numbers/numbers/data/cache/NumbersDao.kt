package com.example.numbers.numbers.data.cache

import androidx.room.*

@Dao
interface NumbersDao {

    @Query("SELECT * FROM numbers_table ORDER BY date DESC")
    fun allNumbers(): List<NumberCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(number: NumberCache)

    @Query("SELECT * FROM numbers_table WHERE number = :number")
    fun number(number: String): NumberCache?
}