package com.example.jeepitybasic

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_route.*

class Route   : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        val route : TextView = findViewById<TextView>(R.id.route)
     //   val picOfPlace : ImageView = findViewById(R.id.imageView)
        val msg : String? = intent.getStringExtra( "offrout")


        route.setText(msg)

      //  val picOfPlaces = intent.getStringExtra("picture")

     //   picOfPlace.setBackgroundResource(picOfPlaces)

        val imageView: ImageView = findViewById(R.id.imageView)
        val bundle: Bundle? = intent.getBundleExtra("resId")
        val resId: Int = bundle!!.getInt("resId")
        imageView.setImageResource(resId)
    }
}

private fun View.setBackgroundResource(picOfPlaces: Any?) {
    TODO("Not yet implemented")
}



