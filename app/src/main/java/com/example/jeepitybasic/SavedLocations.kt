package com.example.jeepitybasic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jeepitybasic.models.Place
import com.example.jeepitybasic.models.UserMap

const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
private const val TAG ="SavedLocations"

class SavedLocations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations) //
        supportActionBar?.hide()
   //MAIN ACTIVITY
        val rvMaps: RecyclerView = findViewById<RecyclerView>(R.id.rvMaps) //

        val userMaps = generateSampleData()
        //Set layout manager on recycler view
        LinearLayoutManager (this).also { rvMaps.layoutManager =it } //
//
        //set adapter on the recycler view
        MapsAdapter (this, userMaps, object: MapsAdapter.OnClickListener{

            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                //when a user taps on view in RV, navigate to new activity
                val intent = Intent(this@SavedLocations, MapsMarkerActivity::class.java) //
               intent.putExtra(EXTRA_USER_MAP, userMaps[position]) //
                startActivity(intent)

            }

        }) .also { rvMaps.adapter = it }
        //rvMaps.onAdapterChanged(UserMap)

    }
    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "Igorot Park",
                listOf(
                    Place("Igorot Park", "Jeepney Station for Campo Sioco, Bakakeng, and PNR", 16.413044213568572, 120.59468018279351)
                )
            ),
            UserMap("Jacinto Street",
                listOf(
                    Place("Peoples park", "Jeepney Station for Pacdal, Maria Basa, Minesview", 16.414759526813487, 120.59598212022799)
                )),
            UserMap("Kayang Street",
                listOf(
                    Place("Abanao Square", "Jeepney Station for all Naguillian Bound Jeepneys", 16.41455174371114, 120.59391148500853)
                )),
            UserMap("Govenor Pack Road",
                listOf(
                    Place("Govenor Pack Road", "Jeepney Station for Minesview", 16.4099107873973, 120.59835200645078),
                    )
            ),
            UserMap("Magsaysay Avenue",
                listOf(
                    Place("Magsaysay Avenue", "Jeepney Station for La Trinidad bound jeepneys", 16.417596565668706, 120.59542630067823),
                )
            ),
            UserMap("Harrison Road",
                listOf(
                    Place("Harrison Road", "Jeepney Station for Aurora Hill and Trancoville", 16.41227709207252, 120.59611841616801)
                )
            ),
            UserMap("Veterans Loop",
                listOf(
                    Place("Patriotic", "Jeepney Station", 16.411277, 120.597505)
                )
            ),
            UserMap("SkyWorld",
                listOf(
                    Place("Calderon Street", "Jeepney Station for Loakan", 16.412315, 120.597042)
                )
            ),
            UserMap("KFC Lower Session",
                listOf(
                    Place("Lower Session", "Jeepney Station for Camp 7 and Camp 8", 16.413502, 120.595389)
                )
            ),
            UserMap("Victory Liner",
                listOf(
                    Place("Marcoville Street", "Jeepney Station for Camp 6", 16.405062, 120.602546)
                )
            ),
            UserMap("Pinesview Bridge",
                listOf(
                    Place("La Trinidad Pinesview", "Jeepney Station for Camp Mt Kalugong", 16.405062, 120.602546)
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