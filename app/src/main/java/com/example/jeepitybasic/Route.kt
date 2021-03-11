package com.example.jeepitybasic

import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Route   : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        val route : TextView = findViewById<TextView>(R.id.route)

        val msg : String? = intent.getStringExtra( "offrout")


        route.setText(msg)




    }
}