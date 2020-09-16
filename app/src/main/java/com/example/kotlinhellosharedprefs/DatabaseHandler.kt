package com.example.kotlinhellosharedprefs

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "AppDatabase"
        private val TABLE_CONTACTS = "AppTable"
        private val KEY_COLOR = "color"
        private val KEY_COUNT = "count"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "( id INTEGER, " + KEY_COLOR + " INTEGER, " + KEY_COUNT + " INTEGER" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addEmployee(data: Data):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id", 1)
        contentValues.put(KEY_COLOR, data.color)
        contentValues.put(KEY_COUNT, data.count) // EmpModelClass Name
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewEmployee():Data?{
        var data:Data? = null
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        var color: Int
        var count: Int
        if (cursor!!.moveToFirst()) {
            do {
                color = cursor.getInt(cursor.getColumnIndex("color"))
                count = cursor.getInt(cursor.getColumnIndex("count"))
                data = Data(1, color, count)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return data
    }
    //method to update data
    fun updateEmployee(data: Data):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_COLOR, data.color)
        contentValues.put(KEY_COUNT, data.count) // EmpModelClass Name

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"id=1",null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}