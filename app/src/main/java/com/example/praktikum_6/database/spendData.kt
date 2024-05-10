package com.example.praktikum_6.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Database

@Entity
data class spendData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("nama")
    val nama: String,
    @ColumnInfo("value")
    val value: String
)
