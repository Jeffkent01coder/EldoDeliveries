package com.jeff.eldodeliveries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Items::class], version = 1)
abstract class El_Database : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var instance: El_Database? = null
        private var LOCK = Any()

        @InternalCoroutinesApi
        operator fun invoke(context: Context) = instance ?: kotlinx.coroutines.internal.synchronized(
            LOCK
        ) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                El_Database::class.java,
                "Eldo_db"
            ).build()
    }
}