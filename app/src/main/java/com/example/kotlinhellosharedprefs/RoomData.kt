package com.example.kotlinhellosharedprefs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_table")
data class RoomData (
                     @PrimaryKey @ColumnInfo(name = "id") var id: Int = 1,
                     @ColumnInfo(name = "color") var color: Int,
                     @ColumnInfo(name = "count") var count: Int)