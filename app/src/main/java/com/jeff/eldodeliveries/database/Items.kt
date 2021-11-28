package com.jeff.eldodeliveries.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
class Items (

        @ColumnInfo(name = "ItemName")
        var ItemName : String,

        @ColumnInfo(name = "Location")
        var location : String,

        @ColumnInfo(name = "Destination")
        var destination : String

        )
{
        @PrimaryKey(autoGenerate = true)
        var id : Int? = null
}