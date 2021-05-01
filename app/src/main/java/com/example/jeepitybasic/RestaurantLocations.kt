package com.example.jeepitybasic

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jeepitybasic.models.Place
import com.example.jeepitybasic.models.UserMap

const val EXTRA_USER_MAP2 = "EXTRA_USER_MAP"
private const val TAG ="SavedLocations"

class RestaurantLocations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_locations)
        supportActionBar?.hide()
        //MAIN ACTIVITY
        val rvMaps2: RecyclerView = findViewById<RecyclerView>(R.id.rvMaps2)

        val userMaps = generateSampleData()
        //Set layout manager on recycler view
        LinearLayoutManager (this).also { rvMaps2.layoutManager =it }
//
        //set adapter on the recycler view
        MapsAdapter (this, userMaps, object: MapsAdapter.OnClickListener{

            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                //when a user taps on view in RV, navigate to new activity
                val intent = Intent(this@RestaurantLocations, MapsMarkerActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP2, userMaps[position])
                startActivity(intent)

            }

        }) .also { rvMaps2.adapter = it }
        //rvMaps.onAdapterChanged(UserMap)

    }
    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "Abonabil Diner",
                listOf(
                    Place("Abonabil Diner", "Arabic Food", 16.4211370797189, 120.5969260966451)
                )
            ),
            UserMap("Sajj",
                listOf(
                    Place("Sajj Shawarma", "Arabic Food & Spices", 16.42318151360115, 120.59356209365284)
                )),
            UserMap("Grumpy Joe",
                listOf(
                    Place("Grumpy Joe", "Italian-American food", 16.4098, 120.6032)
                )),
            UserMap("Lemon and Olives",
                listOf(
                    Place("Lemon and Olives", "Greek cuisine", 16.4109, 120.6232)
                )
            ),
            UserMap("Pizza Hut",
                listOf(
                    Place("Pizza Hut", "Pizza", 16.4114, 120.5987),
                    Place("Pizza Hut", "Pizza", 16.4147, 120.5907),
                    Place("Pizza Hut", "Pizza", 16.4090, 120.5998)
                )
            ),
            UserMap("Mcdonald's",
                listOf(
                    Place("McDonald's", "American, Fast Food", 16.4118, 120.5982),
                    Place("McDonald's", "American, Fast Food", 16.4127, 120.5909),
                    Place("McDonald’s", "American, Fast Food", 16.4094, 120.5998),
                    Place("McDonald’s", "American, Fast Food", 16.4159, 120.5961),
                    Place("McDonald’s", "American, Fast Food", 16.4188, 120.5967)
                )
            ),
            UserMap("Greenwich",
                listOf(
                    Place("Greenwich", "Italian Food, Pizza", 16.4140, 120.5946),
                    Place("Greenwich", "Italian Food, Pizza", 16.4090, 120.5994),
                    Place("Greenwich", "Italian Food, Pizza", 16.4122, 120.5976)
                )
            )

        )
    }

}