package com.example.databasecenter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
        db?.execSQL(SQL_REGISTER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        db?.execSQL(SQL_REGISTER_DELETE)
        onCreate(db)
    }
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "model.db"

        //Table to store person details
        private const val SQL_CREATE_TABLE = "CREATE TABLE ${Model.ModelEntry.TABLE_NAME} (" +
                "${Model.ModelEntry.COLUMN_ID} TEXT," +
                "${Model.ModelEntry.COLUMN_NAME} TEXT," +
                "${Model.ModelEntry.COLUMN_PHONENUMBER} TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Model.ModelEntry.TABLE_NAME}"

        //Table to store new register member details
        private const val SQL_REGISTER_TABLE = "CREATE TABLE ${Model.ModelEntry.SECOND_TABLE} (" +
                "${Model.ModelEntry.COLUMN_FULLNAME} TEXT," +
                "${Model.ModelEntry.COLUMN_EMAILADDRESS} TEXT," +
                "${Model.ModelEntry.COLUMN_PASSWORD} TEXT," +
                "${Model.ModelEntry.COLUMN_CONFIRMPASSWORD} TEXT)"
        private const val SQL_REGISTER_DELETE = "DROP TABLE IF EXISTS ${Model.ModelEntry.SECOND_TABLE}"

    }

}