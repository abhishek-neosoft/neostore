package com.example.webworks.neostore.sqldatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SqlDatabaseManage(var context: Context?) : SQLiteOpenHelper(context, "save_address.db", null, 1) {

    val TABLE_NAME: String = "users_address"
    val COL_1 = "ID"
    val COL_2 = "ACCESS_TOKEN"
    val COL_3 = "ADDRESS"

    override fun onCreate(db: SQLiteDatabase?) {

        //db!!.execSQL("create table" +TABLE_NAME+"(ACCESS_TOKEN TEXT,ADDRESS TEXT)")
        db!!.execSQL("create table ${TABLE_NAME} (ID INTEGER PRIMARY KEY AUTOINCREMENT,ACCESS_TOKEN TEXT,ADDRESS TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
        Toast.makeText(context, "cancle", Toast.LENGTH_LONG).show()
    }

    fun insertData(accessToken: String, address: String): Boolean {

        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, accessToken)
        contentValues.put(COL_3, address)

        val result = db.insert(TABLE_NAME, null, contentValues)
        if (result.equals(-1))
            return false
        else
            return true
    }

    fun fetchData(accessToken: String): Cursor {
        val db = writableDatabase
        return db.rawQuery("select * from " + TABLE_NAME + " WHERE " + COL_2 + "='" + accessToken + "'", null)
    }

    fun deleteData(position: Int): Boolean {

        val db = writableDatabase

        val detete = db.delete(TABLE_NAME ,  COL_1 + "=?" ,arrayOf((position+1).toString()))
        return true
    }
}