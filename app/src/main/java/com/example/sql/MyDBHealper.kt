package com.example.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf

class MyDBHealper(val context: Context) :SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    companion object{

        val DB_NAME = "School"
        val DB_VERSION = 1
        val TABLE = "Student"
        val COLUMN_SNO = "Sno"
        val COLUMN_NAME =" Name"
        val COLUMN_CLASS =" Class"
        val COLUMN_Sec ="Section"


    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL("Create Table $TABLE ($COLUMN_SNO integer primary key autoincrement," +
                    "$COLUMN_NAME text not null," +
                    "$COLUMN_CLASS text not null," +
                    "$COLUMN_Sec text) ")
        }

    }
    fun addStudent(Name:String,Class:String,Sec:String){
 val db = writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME,Name)
        cv.put(COLUMN_CLASS,Class)
        cv.put(COLUMN_Sec,Sec)

        db.insert(TABLE ,null,cv)
    }
    fun getStudents():ArrayList<DataModel>{
 val Rdb = readableDatabase

      val cursor = Rdb.rawQuery("select * from $TABLE",null)
        val Arraylist = ArrayList<DataModel>()
        while (cursor.moveToNext()){
            val sno =  cursor.getInt(0)
            val name = cursor.getString(1)
            val Class = cursor.getString(2)
            val sec = cursor.getString(3)
            val DataModel = DataModel(sno,name,Class,sec)
            Arraylist.add(DataModel)
        }
return  Arraylist
    }

    fun updates(sno:Int,update_name:String,update_class:String,update_sec:String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME,update_name)
        cv.put(COLUMN_CLASS,update_class)
        cv.put(COLUMN_Sec,update_sec)
        Log.d("datavalues","${sno},${update_name}, ${update_class},${update_sec}")
        db.update(TABLE,cv,"$COLUMN_SNO ='$sno'", arrayOf())


    }
    fun Delete(Sno:Int){
        var db =writableDatabase
        db.delete(TABLE,"$COLUMN_SNO =?", arrayOf("$Sno"))
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}