package com.example.databasecenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var id: EditText
    private lateinit var mfullName: EditText
    private lateinit var mphoneNumber : EditText
    private lateinit var submitBtn: Button
    private lateinit var displayTextView: TextView

    var dbHelper = DatabaseHelper(this)
    var missile = AlertDialog()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.idEditText)
        mfullName = findViewById(R.id.fullNameEditText)
        mphoneNumber = findViewById(R.id.mobileNumberEditText)
        submitBtn = findViewById(R.id.submitBtn)
        displayTextView = findViewById(R.id.displayTextView)


        submitBtn.setOnClickListener{
            missile.show(supportFragmentManager, "dialog" )


            /*
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

            if (values.equals(-1)){
                Toast.makeText(applicationContext, "Problem Occur!", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(applicationContext, "Data added Successfully", Toast.LENGTH_LONG).show()
            }

             */
        }

        showDataBtn.setOnClickListener {
            val db = dbHelper.readableDatabase

            val projection = arrayOf(Model.ModelEntry.COLUMN_ID, Model.ModelEntry.COLUMN_NAME, Model.ModelEntry.COLUMN_PHONENUMBER)

            val cursor = db.query(
                Model.ModelEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )
           cursor.moveToFirst()
           displayTextView.text = cursor.getString(0) + cursor.getString(1) + cursor.getString(2)

        }


    }



}