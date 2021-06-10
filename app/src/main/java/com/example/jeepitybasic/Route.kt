package com.example.jeepitybasic

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_route.*

class Route   : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        val route : TextView = findViewById<TextView>(R.id.route)
     //   val picOfPlace : ImageView = findViewById(R.id.imageView)
        val msg : String? = intent.getStringExtra( "offrout")

        println("got this far 6")
        route.setText(msg)
        println("got this far 7")
      //  val picOfPlaces = intent.getStringExtra("picture")

     //   picOfPlace.setBackgroundResource(picOfPlaces)

   //     val imageView: ImageView = findViewById(R.id.imageViewOR)
    //    val bundle: Bundle? = intent.getBundleExtra("resId")
    //    val resId: Int = bundle!!.getInt("resId")
    //    imageView.setImageResource(resId)
    }
}





