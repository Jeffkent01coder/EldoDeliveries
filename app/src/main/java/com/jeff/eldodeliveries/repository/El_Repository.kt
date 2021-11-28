package com.jeff.eldodeliveries.repository

import com.jeff.eldodeliveries.database.El_Database
import com.jeff.eldodeliveries.database.Items

class El_Repository (private  val db: El_Database) {

    suspend fun insert(items: Items) = db.getDao().insert(items)
    suspend fun delete(items: Items) = db.getDao().delete(items)

    fun getAllItems() = db.getDao().getAllItems()
}