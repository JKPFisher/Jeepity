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
const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
private const val TAG ="SavedLocations"

class SavedLocations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)
        supportActionBar?.hide()
   //MAIN ACTIVITY
        val rvMaps: RecyclerView = findViewById<RecyclerView>(R.id.rvMaps)

        val userMaps = generateSampleData()
        //Set layout manager on recycler view
        LinearLayoutManager (this).also { rvMaps.layoutManager =it }
//
        //set adapter on the recycler view
        MapsAdapter (this, userMaps, object: MapsAdapter.OnClickListener{

            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                //when a user taps on view in RV, navigate to new activity
                val intent = Intent(this@SavedLocations, MapsMarkerActivity::class.java)
               intent.putExtra(EXTRA_USER_MAP, userMaps[position])
                startActivity(intent)

            }

        }) .also { rvMaps.adapter = it }
        //rvMaps.onAdapterChanged(UserMap)

    }
    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "Jeepney Stations",
                listOf(
                    Place("Igorot Park", "Jeepney Station", 16.413044213568572, 120.59468018279351),
                    Place("Abanao Square", "Jeepney Station", 16.41455174371114, 120.59391148500853),
                    Place("Harrison Road", "Jeepney Station", 16.41227709207252, 120.59611841616801),
                    Place("Govenor Pack Road", "Jeepney Station", 16.4099107873973, 120.59835200645078),
                    Place("Magsaysay Avenue", "Jeepney Station", 16.417596565668706, 120.59542630067823),
                    Place("Centermall", "Jeepney Station", 16.415790418102972, 120.59704099044497),
                    Place("Peoples park", "Jeepney Station", 16.414759526813487, 120.59598212022799)

                )
            ),
            UserMap("Tourist Spots",
                listOf(
                    Place("Tokyo", "Overnight layover", 35.67, 139.65),
                    Place("Ranchi", "Family visit + wedding!", 23.34, 85.31),
                    Place("Singapore", "Inspired by \"Crazy Rich Asians\"", 1.35, 103.82)
                )),
            UserMap("Restaurants",
                listOf(
                    Place("Gardens by the Bay", "Amazing urban nature park", 1.282, 103.864),
                    Place("Jurong Bird Park", "Family-friendly park with many varieties of birds", 1.319, 103.706),
                    Place("Sentosa", "Island resort with panoramic views", 1.249, 103.830),
                    Place("Botanic Gardens", "One of the world's greatest tropical gardens", 1.3138, 103.8159)
                )
            ),
            UserMap("Universities",
                listOf(
                    Place("Chicago", "Urban center of the midwest, the \"Windy City\"", 41.878, -87.630),
                    Place("Rochester, Michigan", "The best of Detroit suburbia", 42.681, -83.134),
                    Place("Mackinaw City", "The entrance into the Upper Peninsula", 45.777, -84.727),
                    Place("Michigan State University", "Home to the Spartans", 42.701, -84.482),
                    Place("University of Michigan", "Home to the Wolverines", 42.278, -83.738)
                )
            )
        )
    }

}