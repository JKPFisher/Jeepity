package com.example.jeepitybasic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OfflineRoutes  : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_routes)


        val minesview: Button = findViewById<Button>(R.id.orMinesView)
        val botanicalgarden: Button = findViewById<Button>(R.id.orbotanicalgarden)





        minesview.setOnClickListener {
            val intent = Intent(this, Route::class.java)
            val mines_view: String = getString(R.string.directions_minesview)
            //val imageViewq: ImageView = findViewById(R.drawable.minesview)
           intent.putExtra("offrout", mines_view)

            startActivity(intent)

            fun sendImage(view: View) {
                val intent = Intent(this@OfflineRoutes, Route::class.java)
                intent.putExtra("resId", R.drawable.minesview)
                startActivity(intent)
            }
        }
        botanicalgarden.setOnClickListener {
            val intent = Intent(this, Route::class.java)
            val botanical: String = getString(R.string.directions_botanical)
            intent.putExtra("offrout", botanical)

            startActivity(intent)
        }

    }
}



