package com.example.jeepitybasic

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*



class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



       ahbutton1.setOnClickListener { val intent = Intent(this,
            SavedLocations::class.java)
            startActivity(intent)}

        ahbutton.setOnClickListener { val intent = Intent(this,
            RestaurantLocations::class.java)
            startActivity(intent)}

        ahbutton3.setOnClickListener { val intent = Intent(this,
            UniversityLocations::class.java)
            startActivity(intent)}

        ahbutton2.setOnClickListener { val intent = Intent(this,
            TouristLocations::class.java)
            startActivity(intent)}

        ahbutton4.setOnClickListener { val intent = Intent(this,
            OfflineRoutes::class.java)
            startActivity(intent)}

        ahbutton5.setOnClickListener { val intent = Intent(this,
            OfflineTranslation::class.java)
            startActivity(intent)}

    }



}

