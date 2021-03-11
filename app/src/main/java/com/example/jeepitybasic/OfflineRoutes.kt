package com.example.jeepitybasic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OfflineRoutes  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_routes)


        val minesview: Button = findViewById<Button>(R.id.orMinesView)
        val botanicalgarden: Button = findViewById<Button>(R.id.orbotanicalgarden)





        minesview.setOnClickListener {
            val intent = Intent(this, Route::class.java)
            val mines_view: String = getString(R.string.directions_minesview)
           intent.putExtra("offrout", mines_view)

            startActivity(intent)
        }
        botanicalgarden.setOnClickListener {
            val intent = Intent(this, Route::class.java)
            val botanical: String = getString(R.string.directions_botanical)
            intent.putExtra("offrout", botanical)

            startActivity(intent)
        }

    }
}

