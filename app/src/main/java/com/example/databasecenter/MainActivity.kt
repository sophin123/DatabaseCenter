package com.example.databasecenter

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mid: EditText
    private lateinit var mfullName: EditText
    private lateinit var mphoneNumber: EditText
    private lateinit var submitBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var displayTextView: TextView

    private lateinit var musername: EditText
    private lateinit var mpassword: EditText


    var dbHelper = DatabaseHelper(this)
    var missile = AlertDialog()

    var username = ""
    var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mid = findViewById(R.id.idEditText)
        mfullName = findViewById(R.id.fullNameEditText)
        mphoneNumber = findViewById(R.id.mobileNumberEditText)
        submitBtn = findViewById(R.id.submitBtn)
        registerBtn = findViewById(R.id.registerBtn)
        displayTextView = findViewById(R.id.displayTextView)


        musername = findViewById(R.id.usernameEditText)
        mpassword = findViewById(R.id.passwordEditText)


        submitBtn.setOnClickListener {
            var id: String? = mid?.text?.toString()
            var fullName: String = mfullName.text.toString()
            var phoneNumber = mphoneNumber.text.toString()


            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(Model.ModelEntry.COLUMN_ID, id)
                put(Model.ModelEntry.COLUMN_NAME, fullName)
                put(Model.ModelEntry.COLUMN_PHONENUMBER, phoneNumber)
            }


            val newRowId = db?.insert(Model.ModelEntry.TABLE_NAME, null, values)

            if (values.equals(-1)) {
                Toast.makeText(applicationContext, "Problem Occur!", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(applicationContext, "Data added Successfully", Toast.LENGTH_LONG)
                    .show()
            }

            mid.text.clear()
            mfullName.text.clear()
            mphoneNumber.text.clear()
        }




        registerBtn.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        showDataBtn.setOnClickListener {

            getUsername()

            missile.show(supportFragmentManager, "dialog")


        }


    }

    @SuppressLint("SetTextI18n")
    fun getUsername() {

        username = musername.text.toString()
        password = mpassword.text.toString()

        musername.text.clear()
        mpassword.text.clear()


        val emailAddressFromRegister = intent.getStringExtra("email")
        val passwordFromRegister = intent.getStringExtra("password")

        Log.i("Email", "Email Address from register : $emailAddressFromRegister")
        Log.i("Password", "Password Address from register : $passwordFromRegister")

        //Read Database

        val db = dbHelper.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM personal_details", null)

        val columnIndexId = cursor.getColumnIndex("id")
        val columnIndexFullName = cursor.getColumnIndex("name")
        val columnIndexPhoneNumber = cursor.getColumnIndex("phoneNumber")

        if (username == emailAddressFromRegister && password == passwordFromRegister) {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val Id = cursor.getString(columnIndexId)
                        val FullName = cursor.getString(columnIndexFullName)
                        val PhoneNumber = cursor.getString(columnIndexPhoneNumber)

                        displayTextView.text = "ID: $Id \n FullName: $FullName \n PhoneNumber: $PhoneNumber"

                    } while (cursor.moveToNext())
                }
            }
        }else{
            Toast.makeText(applicationContext, "Email Address and Username do not match", Toast.LENGTH_LONG).show()
        }
    }
}