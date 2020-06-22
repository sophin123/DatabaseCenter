package com.example.databasecenter

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var id: EditText
    private lateinit var mfullName: EditText
    private lateinit var mphoneNumber : EditText
    private lateinit var submitBtn: Button

    var dbHelper = DatabaseHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.idEditText)
        mfullName = findViewById(R.id.fullNameEditText)
        mphoneNumber = findViewById(R.id.mobileNumberEditText)
        submitBtn = findViewById(R.id.submitBtn)


        submitBtn.setOnClickListener{
            var id: String = id.text.toString()
            var fullName: String = mfullName.text.toString()
            var phoneNumber = mphoneNumber.text.toString()


            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(Model.ModelEntry.COLUMN_ID, id)
                put(Model.ModelEntry.COLUMN_NAME, fullName)
                put(Model.ModelEntry.COLUMN_PHONENUMBER, phoneNumber)
            }

            val newRowId = db?.insert(Model.ModelEntry.TABLE_NAME, null, values)

            if (values != null){
                Toast.makeText(applicationContext, "Data added Successfully", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(applicationContext, "Problem Occur!", Toast.LENGTH_LONG).show()
            }
        }


    }

}