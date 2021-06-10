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
import androidx.core.content.ContextCompat.startActivity

class OfflineRoutes  : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_routes)


        val minesview: Button = findViewById<Button>(R.id.orMinesView)
        val botanicalgarden: Button = findViewById<Button>(R.id.orbotanicalgarden)

        println("got this far 1")

      //  fun sendImage(view: View) {

     //   }

        minesview.setOnClickListener {
           val intent = Intent(this, Route::class.java)
            val mines_view: String = getString(R.string.directions_minesview)
         //   val imageView: ImageView = findViewById(R.drawable.minesview)
            println("got this far 2")
          intent.putExtra("offrout", mines_view)
            println("got this far 3")
           startActivity(intent)
            println("got this far 4")

        }
        botanicalgarden.setOnClickListener {
            val intent = Intent(this, Route::class.java)
            val botanical: String = getString(R.string.directions_botanical)
            intent.putExtra("offrout", botanical)
            println("got this far 2")
            startActivity(intent)
        }

    }
}

private fun Intent.putExtra(s: String, minesView: String, s1: String, minesview: Int) {
    TODO("Not yet implemented")

}


