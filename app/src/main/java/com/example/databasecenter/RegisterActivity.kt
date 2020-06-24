package com.example.databasecenter

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.LoginFilter
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var fullName: EditText
    private lateinit var emailAddress: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var createBtn: Button



    private lateinit var checkBox: CheckBox
    private lateinit var textView: TextView

    var dbHelper = DatabaseHelper(this)

    var emailFromRegister = ""
    var passwordFromRegister = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fullName = findViewById(R.id.nameEditText)
        emailAddress = findViewById(R.id.emailAddressEditText)
        password = findViewById(R.id.passwordEditText)
        confirmPassword = findViewById(R.id.confirmPasswordEditText)
        createBtn = findViewById(R.id.createBtn)

        checkBox = findViewById(R.id.checkBox)
        textView = findViewById(R.id.termsConditions)



        checkBox.text = ""

        textView.text = Html.fromHtml("Agree with " +
                "<a href='id.web.freelancer.example.TCActivity://Kode'>TERMS AND CONDITIONS</a>")
        textView.isClickable = true
        textView.movementMethod = LinkMovementMethod.getInstance()

        createBtn.setOnClickListener {
            getData()
        }

    }

    fun addData(){
        val fullName = fullName.text.toString()
        val emailAddress = emailAddress.text.toString()
        val password = password.text.toString()
        val confirmPassword = confirmPassword.text.toString()

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(Model.ModelEntry.COLUMN_FULLNAME, fullName)
            put(Model.ModelEntry.COLUMN_EMAILADDRESS, emailAddress)
            put(Model.ModelEntry.COLUMN_PASSWORD, password)
            put(Model.ModelEntry.COLUMN_CONFIRMPASSWORD, confirmPassword)
        }

        val newRowId = db?.insert(Model.ModelEntry.SECOND_TABLE, null, values)

        if (values.equals(-1)){
            Toast.makeText(applicationContext, "Problem Occur!", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(applicationContext, "Data added Successfully", Toast.LENGTH_LONG).show()
        }

        Log.i("Button Pressed", "Button Pressed")
    }

    fun getData(){
        val db = dbHelper.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM registers", null)

        val emailColumnIndex: Int = cursor.getColumnIndex("email")
        val passwordColumnIndex: Int = cursor.getColumnIndex("password")



        if(cursor!=null){
            if(cursor.moveToFirst()) {
                do {
                    emailFromRegister = cursor.getString(emailColumnIndex)
                    passwordFromRegister = cursor.getString(passwordColumnIndex)

                    Log.i("Email", cursor.getString(emailColumnIndex))
                    Log.i("Password", cursor.getString(passwordColumnIndex))
                    cursor.moveToNext()
                } while (cursor.moveToNext())
            }
        }

        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", emailFromRegister)
        intent.putExtra("password", passwordFromRegister)
        startActivity(intent)


    }
}