package com.example.jeepitybasic

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_offline_routes.*
import kotlinx.android.synthetic.main.ts_minesview_activity.*

class OfflineRoutes  : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_offline_routes)



        orMinesView.setOnClickListener       {
            setContentView(R.layout.ts_minesview_activity)
            this.supportActionBar?.hide()

            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                startActivity(intent)}


        }
        orbotanicalgarden.setOnClickListener {
            setContentView(R.layout.ts_botanical_activity)
        }
        orLionsHead.setOnClickListener {
            setContentView(R.layout.ts_lionshead_activity)
        }
        orbellchurch.setOnClickListener{
            setContentView(R.layout.ts_bellchurch_activity)
        }

    }

}



