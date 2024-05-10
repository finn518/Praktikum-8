package com.example.praktikum_6.database

import android.content.Context
import androidx.room.Database
import androidx.room.*

@Database(
    entities = [spendData::class],
    version = 1
)
abstract class DB : RoomDatabase(){

    abstract fun Dao() : DAO

    companion object {

        @Volatile private var instance : DB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DB::class.java,
            "menu.db"
        ).build()

        }
}