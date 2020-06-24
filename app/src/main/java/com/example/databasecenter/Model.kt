package com.example.databasecenter

import android.provider.BaseColumns

object Model{
    object ModelEntry: BaseColumns{
        const val TABLE_NAME = "personal_details"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONENUMBER = "phoneNumber"

        const val SECOND_TABLE = "registers"
        const val COLUMN_FULLNAME = "fullname"
        const val COLUMN_EMAILADDRESS = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_CONFIRMPASSWORD = "confirm_password"

    }
}