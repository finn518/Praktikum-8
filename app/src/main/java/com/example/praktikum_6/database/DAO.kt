package com.example.praktikum_6.database


import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface DAO {
    @Insert
    fun addSpend(spend: spendData)
    @Delete
    fun delSpend(spend: spendData)
    @Query("SELECT * FROM spendData")
    fun getSpend(): List<spendData>
}