package com.appdev.tzwallhavenapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.appdev.tzwallhavenapp.adapter.Item

class DBOpenHelper(context: Context?,
                   factory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME,
                factory, DATABASE_VERSION) {

   override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_URL
                + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

   override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

   fun addItem(item: Item) {
        val values = ContentValues()
        values.put(COLUMN_URL, item.toString())
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun selectAll(): ArrayList<Item> {
        val db = this.readableDatabase
        val cursor: Cursor =
            db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val arr: ArrayList<Item> = ArrayList()
        cursor.moveToFirst()
        if (!cursor.isAfterLast) {
            do {
                arr.add(Item(cursor.getString(1)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return arr
    }

   companion object {
       private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ImageUrls.db"
        val TABLE_NAME = "name"
        val COLUMN_ID = "_id"
        val COLUMN_URL = "url"
    }

    fun deleteAll(context: Context?) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        context?.deleteDatabase(DATABASE_NAME)
    }
}