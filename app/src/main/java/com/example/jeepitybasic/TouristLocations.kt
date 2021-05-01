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
            UserMap("Kayang Street",
                listOf(
                    Place("Peoples park", "Jeepney Station", 16.414759526813487, 120.59598212022799)
                )),
            UserMap("Kayang Street",
                listOf(
                    Place("Abanao Square", "Jeepney Station for All Naguillian Bound Jeepneys", 16.41455174371114, 120.59391148500853)
                )),
            UserMap("Govenor Pack Road",
                listOf(
                    Place("Govenor Pack Road", "Jeepney Station", 16.4099107873973, 120.59835200645078),
                )
            ),
            UserMap("Magsaysay Avenue",
                listOf(
                    Place("Magsaysay Avenue", "Jeepney Station", 16.417596565668706, 120.59542630067823),
                )
            ),
            UserMap("Harrison Road",
                listOf(
                    Place("Harrison Road", "Jeepney Station for Aurora Hill and Trancoville", 16.41227709207252, 120.59611841616801)
                )
            ),
            UserMap("Centermall",
                listOf(
                    Place("Centermall", "Jeepney Station", 16.415790418102972, 120.59704099044497)
                )
            )

        )
    }

}