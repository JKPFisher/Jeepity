package com.example.jeepitybasic

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView


class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {
    val user_location: Spinner = findViewById(R.id.spinner)

    //
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos)
// Get Selected Class name from the list


        // Display the selected item into the TextView
        // Display the selected item into the TextView
       // TextView.setText("Selected : $selectedItemText")
    }
        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }
    }