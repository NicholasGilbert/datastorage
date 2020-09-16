package com.example.kotlinhellosharedprefs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataDAO {
    @Query("SELECT * from app_table")
    fun getData(): RoomData

    @Update
    fun update(data: RoomData)

    @Insert
    fun insert(data: RoomData)
}