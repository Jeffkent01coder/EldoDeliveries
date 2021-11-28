package com.jeff.eldodeliveries.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Items)

    @Delete
    suspend fun delete(item: Items)

    @Query("SELECT * FROM Items")
    fun getAllItems(): LiveData<List<Items>>
}