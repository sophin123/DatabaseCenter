package com.example.databasecenter

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalStateException

class AlertDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder = AlertDialog.Builder(it)

                val inflater = requireActivity().layoutInflater

                builder.setView(inflater.inflate(R.layout.dialog_sigin, null))
                    .setPositiveButton("Sign In", null)
                    .setNegativeButton("Cancel", null)

                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }

