package com.example.jeepitybasic

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jeepitybasic.models.Place
import com.example.jeepitybasic.models.UserMap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
const val EXTRA_USER_MAP3 = "EXTRA_USER_MAP"
private const val TAG ="SavedLocations"

class UniversityLocations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_university_locations) //
        supportActionBar?.hide()
        //MAIN ACTIVITY
        val rvMaps: RecyclerView = findViewById<RecyclerView>(R.id.rvMaps3) //

        val userMaps = generateSampleData()
        //Set layout manager on recycler view
        LinearLayoutManager (this).also { rvMaps.layoutManager =it } //
//
        //set adapter on the recycler view
        MapsAdapter (this, userMaps, object: MapsAdapter.OnClickListener{

            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                //when a user taps on view in RV, navigate to new activity
                val intent = Intent(this@UniversityLocations, MapsMarkerActivity::class.java) //
                intent.putExtra(EXTRA_USER_MAP3, userMaps[position]) //
                startActivity(intent)

            }

        }) .also { rvMaps.adapter = it }
        //rvMaps.onAdapterChanged(UserMap)

    }
    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "University of the Cordilleras",
                listOf(
                    Place("University of the Cordilleras", "", 16.4087, 120.5978),
                )
            ),

            UserMap(
                "University of Baguio",
                listOf(
                    Place("University of Baguio", "", 16.4054, 120.5981),
                )
            ),

            UserMap(
                "Baguio Central University",
                listOf(
                    Place("Baguio Central University", "", 16.417222, 120.596667),
                )
            ),

            UserMap(
                "Pines City Colleges",
                listOf(
                    Place("Pines City Colleges", "", 16.4260, 120.5947),
                )
            ),

            UserMap(
                "AMA Computer University",
                listOf(
                    Place("AMA Computer University", "", 14.6653, 121.0125),
                )
            ),

            UserMap(
                "ST. Louis University",
                listOf(
                    Place("ST. Louis University", "", 16.3842, 120.5932),
                )
            ),

            UserMap(
                "University of the Philippines Baguio",
                listOf(
                    Place("University of the Philippines Baguio", "", 16.4052, 120.5986),
                )
            ),

            UserMap(
                "STI College",
                listOf(
                    Place("STI College", "", 16.4212, 120.5971),
                )
            ),


            UserMap(
                "Philippine Women's University - CDCEC - Baguio",
                listOf(
                    Place("Philippine Women's University - CDCEC - Baguio", "", 16.4178, 120.5962),
                )
            ),

            UserMap(
                "Informatics Institute Baguio",
                listOf(
                    Place("Informatics Institute Baguio", "", 16.4181, 120.5963),
                )
            ),

            UserMap(
                "National Baguio University",
                listOf(
                    Place("National Baguio University", "", 16.4226, 120.5906),
                )
            ),

            UserMap(
                "Philippine Military Academy",
                listOf(
                    Place("Philippine Military Academy", "", 16.3609, 120.6194),
                )
            ),

            UserMap(
                "Remnant International College",
                listOf(
                    Place("Remnant International College", "", 16.3891, 120.5995),
                )
            ),

            UserMap(
                "International Christian College",
                listOf(
                    Place("International Christian College", "", 16.3167, 120.5500),
                )
            ),

            UserMap(
                "Keystone College",
                listOf(
                    Place("Keystone College", "", 16.4016, 120.5942),
                )
            )
        )

    }

}