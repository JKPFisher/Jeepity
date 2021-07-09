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
const val EXTRA_USER_MAP4 = "EXTRA_USER_MAP"
private const val TAG ="SavedLocations"

class TouristLocations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist) //
        supportActionBar?.hide()
        //MAIN ACTIVITY
        val rvMaps: RecyclerView = findViewById<RecyclerView>(R.id.rvMaps4) //

        val userMaps = generateSampleData()
        //Set layout manager on recycler view
        LinearLayoutManager (this).also { rvMaps.layoutManager =it } //
//
        //set adapter on the recycler view
        MapsAdapter (this, userMaps, object: MapsAdapter.OnClickListener{

            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                //when a user taps on view in RV, navigate to new activity
                val intent = Intent(this@TouristLocations, MapsMarkerActivity::class.java) //
                intent.putExtra(EXTRA_USER_MAP4, userMaps[position]) //
                startActivity(intent)

            }

        }) .also { rvMaps.adapter = it }
        //rvMaps.onAdapterChanged(UserMap)

    }
    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "Botanical Garden",
                listOf(
                    Place("Igorot Park", "Jeepney Station for Campo Sioco, Bakakeng, and PNR", 16.413044213568572, 120.59468018279351)
                )
            ),
        UserMap("Mines View Park",
        listOf(
            Place("Mines View Terminal", "Jeepney Station", 16.4212241, 120.6248027)

            )
            ),


        UserMap("Camp John Hay",
        listOf(
            Place("Igorot Park", "Jeepney Station", 16.413044213568572, 120.59468018279351)

        )
        ),

        UserMap("Philippine Military Academy",
        listOf(
            Place("Igorot Park", "Jeepney Station", 16.413044213568572, 120.59468018279351)

        )
        ),

        UserMap("Strawberry Farms",
        listOf(
            Place("La Trinidad", "Jeepney Station", 16.417500, 120.595800)

        )
        ),
        UserMap("Stobosa",
        listOf(
            Place("La Trinidad", "Jeepney Station", 16.417500, 120.595800)

        )
        ),
        UserMap("Tree Top Adventure",
        listOf(
            Place("Igorot Park", "Jeepney Station", 16.413044213568572, 120.59468018279351)
        )
        ),
        UserMap("Bell Church",
        listOf(
            Place("La Trinidad", "Jeepney Station", 16.417500, 120.595800)
        )
        ),
        UserMap("Tam-awan Village",
        listOf(
            Place("Jeepney Terminal to Tam Awan Village", "Jeepney Station", 16.41521310857069, 120.59354728671913)
        )
        ),
        UserMap("Wright Park",
        listOf(
            Place("Mines View Terminal", "Jeepney Station", 16.4212241, 120.6248027)
        )
        ),
        UserMap("The Mansion",
        listOf(
            Place("Mines View Terminal", "Jeepney Station", 16.4212241, 120.6248027)
        )
        )
        )
    }

}