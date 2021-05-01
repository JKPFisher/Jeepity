package com.example.jeepitybasic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        supportActionBar?.hide()

       Handler().postDelayed({
           val intent = Intent (this@Splash, Home::class.java)
           startActivity(intent)

       }, 2000)
    }
}






