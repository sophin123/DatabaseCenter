package com.example.databasecenter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Companion.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "model.db"
        private const val SQL_CREATE_TABLE = "CREATE TABLE ${Model.ModelEntry.TABLE_NAME} (" +
                "${Model.ModelEntry.COLUMN_ID} TEXT," +
                "${Model.ModelEntry.COLUMN_NAME} TEXT," +
                "${Model.ModelEntry.COLUMN_PHONENUMBER} TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Model.ModelEntry.TABLE_NAME}"
    }

}