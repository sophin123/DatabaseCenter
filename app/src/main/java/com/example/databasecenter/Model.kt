package com.example.databasecenter

import android.provider.BaseColumns

object Model{
    object ModelEntry: BaseColumns{
        const val TABLE_NAME = "sophin"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONENUMBER = "phoneNumber"
    }
}