package com.example.kotlinhellosharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mCount: Int = 0
    var mColor: Int = 0
    var mShowCountTextView: TextView? = null
    val COUNT_KEY: String = "count"
    val COLOR_KEY: String = "color"
    lateinit var db: DataDatabase
//    val sharedPrefFile: String = "com.example.android.kotlin_hello_shared_prefs"
//    var mPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mShowCountTextView = count_textview
//        mPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        mColor = ContextCompat.getColor(this, R.color.default_background)

        db = Room.databaseBuilder(applicationContext, DataDatabase::class.java, "data.db").allowMainThreadQueries().build()

        if(db.DataDAO().getData() == null){
            db.DataDAO().insert(RoomData(1, mColor, mCount))
        }
        else{
            val data = db.DataDAO().getData()
            mCount = data!!.count
            mShowCountTextView!!.setText(mCount.toString())
            mColor = data!!.color
            mShowCountTextView!!.setBackgroundColor(mColor)
        }

//        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
//        if (databaseHandler.viewEmployee() != null) {
//            val data = databaseHandler.viewEmployee()
//            mCount = data!!.count
//            mShowCountTextView!!.setText(mCount.toString())
//            mColor = data!!.color
//            mShowCountTextView!!.setBackgroundColor(mColor)
//
//        }
//        else{
//            databaseHandler.addEmployee(Data(mColor, mCount))
//        }

//        mCount = mPreferences!!.getInt(COUNT_KEY, 0)
//        mShowCountTextView!!.setText(String.format("%s", data!!.count))
//        mColor = mPreferences!!.getInt(COLOR_KEY, mColor)
//        mShowCountTextView!!.setBackgroundColor(mColor)
    }

    fun changeBackground(view: View) {
        val color: Int = (view.background as ColorDrawable).color
        mShowCountTextView!!.setBackgroundColor(color)
        mColor = color
    }

    fun countUp(view: View) {
        mCount++
        mShowCountTextView!!.text = String.format("%s", mCount)
    }

    fun reset(view: View) {
        mCount = 0
        mShowCountTextView!!.text = String.format("%s", mCount)

        mColor = ContextCompat.getColor(this, R.color.default_background)
        mShowCountTextView!!.setBackgroundColor(mColor)

//        val preferencesEditor: SharedPreferences.Editor = mPreferences!!.edit()
//        preferencesEditor.clear()
//        preferencesEditor.apply()

//        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
//        databaseHandler.updateEmployee(Data(mColor, mCount))

//        val db = Room.databaseBuilder(applicationContext, DataDatabase::class.java, "data.db").build()
        db.DataDAO().update(RoomData(1, mColor, mCount))
    }

    override fun onPause() {
        super.onPause()

//        val preferencesEditor: SharedPreferences.Editor = mPreferences!!.edit()
//        preferencesEditor.putInt(COUNT_KEY, mCount)
//        preferencesEditor.putInt(COLOR_KEY, mColor)
//        preferencesEditor.apply()

//        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
//        databaseHandler.updateEmployee(Data(mColor, mCount))

//        val db = Room.databaseBuilder(applicationContext, DataDatabase::class.java, "data.db").build()
        db.DataDAO().update(RoomData(1, mColor, mCount))
    }
}