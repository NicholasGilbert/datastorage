package com.example.kotlinhellosharedprefs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomData::class), exportSchema = false, version = 1)
abstract class DataDatabase : RoomDatabase() {
    abstract fun  DataDAO(): DataDAO

    companion object{
        @Volatile
        var INSTANCE: DataDatabase? = null

//        fun getDatabase(context: Context): DataDatabase{
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DataDatabase::class.java,
//                    "data_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }

        private val LOCK = Any()

        operator fun invoke(context: Context)= INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            DataDatabase::class.java, "todo-list.db")
            .build()
    }
}