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
                    Place("Botanical Garden", "Botanical Garden is one of the many green spaces in the city, the Baguio Botanical Garden (renamed Centennial Park) is located between Wright Park and Teacher’s Camp, along Leonard Wood Road. This is a popular attraction in the city where visitors can enjoy the relaxing atmosphere of the verdant surroundings.", 16.4144, 120.6132)
                )
            ),
            UserMap("Mines View Park",
            listOf(
                Place("Mines View Park", "Mines View is one of the oldest and most famous attractions in the City of Pines.The park got its name from the Benguet mountain range where gold, silver and copper were once quarried. It was a mining area for local diggers before the Americans discovered Baguio City.", 16.4196, 120.6279)

            )
        ),
        UserMap("Camp John Hay",
        listOf(
            Place("Camp John Hay", "Camp John Hay is one of the best attractions in the City of Pines. The camp offers tourists tons of fun activities. It highlights the best that Baguio has to offer, which are pine trees, fresh air, cool breeze, and a serene atmosphere. Camp John Hay offers several thrilling rides, including the Superman Ride (zipline), Tree Drop (harnessed free fall), Canopy Ride, and Horse Rides.", 16.3970, 120.6114)

        )
        ),

        UserMap("Strawberry Farm",
        listOf(
            Place("Strawberry Farm", "Those who wish to engage in Strawberry Picking may do so at a minimal fee. Charges will depend on the number of baskets you wish to fill.There are also strawberries for sale and other related products such as strawberry ice-cream and strawberry-taho, as well as other produce.", 16.4580, 120.5866)

        )
        ),
        UserMap("Stobosa",
        listOf(
            Place("Stobosa", "The Colors of StoBoSa is a popular tourist attraction in La Trinidad. Officially named StoBoSa Hillside Homes Artwork, it was the brainchild of the DOT-CAR in collaboration with the Tam-Awan Village group which came up with several design proposals and over 500 residents/volunteers.", 16.4337, 120.5970)

        )
        ),
        UserMap("Tree Top Adventure",
        listOf(
            Place("Tree Top Adventure", "Tree Top Adventure Baguio is one of the most distinctive and amusing recreational parks in the Philippines, and one of the few that offer motorized zip lines and team building facilities. Baguio may have many other tourist spots, but Tree Top Adventure Baguio takes the cake when it comes to adrenaline-pumping action!.", 16.3984, 120.6173)
        )
        ),
        UserMap("Bell Church",
        listOf(
            Place("Bell Church" , "Bell Church is a temple founded by Chinese immigrants from Canton (Guangzhou) in 1960.The serene atmosphere and the view while strolling around the courtyard attract a lot of tourists to the temple, making it a major tourist destination. It also features a small lily pond.", 16.4316, 120.5989)
        )
        ),
        UserMap("Tam-awan Village",
        listOf(
            Place("Tam-awan Village", "Tam-Awan Village, nicknamed Garden in the Sky, was commissioned by the Chanum Foundation in 1998. It aims to create a model village showcasing reconstructed traditional Cordillera houses to make the region’s culture accessible to more people.", 16.4295, 120.5762)
        )
        ),
        UserMap("Wright Park",
        listOf(
            Place("Wright Park", "This is a favorite leisure spot for both locals and tourists. It’s a lush park that offers walking and jogging trails, biking paths, and rest spots. It features a long pond or pool that almost covers the whole stretch of concrete path linking the Wright Park Kiosk and The Mansion facade. It also has a pine tree-covered forest hill adjacent to the horse stable.", 16.4159, 120.6167)
        )
        ),
        UserMap("The Mansion",
        listOf(
            Place("The Mansion", "Wright Park’s long concrete path with a pool leads to the gate of the Mansion House, popularly referred to as The Mansion. It was commissioned in 1908 to serve as the official residence of the US Governor-General in the Philippines. It underwent reconstruction in 1947 after sustaining huge damage during the Second World War.", 16.4124, 120.6215)
        )
        ),

        UserMap("Yellow Trail",
        listOf(
            Place("Yellow Trail", "The Yellow Trail in Camp John Hay in Baguio City is a must see for young and old nature lovers, hikers and mountaineers. This two-kilometer, 2-hour trail is free. The trail is surrounded by pine trees and allows you a good glimpse of the neighboring mountain ranges. This should be one of the refreshing things to do in Baguio.", 16.3970, 120.6114)
        )
        ),

        UserMap("Bell House",
        listOf(
            Place("Bell House", "A beautiful American colonial architecture, the Bell House reflects the lifestyle during its era. It used to be the resthouse of Gen. Franklin Bell. He was the one who transformed Camp John Hay to a military resort.", 16.3991, 120.6183)
        )
        ),

        UserMap("Cemetery of Negativism",
        listOf(
            Place("Cemetery of Negativism", "The Cemetery of Negativism, also known as the Lost Cemetery and was established way back 1980s. The place is a symbolic site for the burying of any kinds of negativeness, in thoughts, emotions, or attitude.", 16.399435, 120.6169975)
        )
        ),

        UserMap("Bencab Museum",
        listOf(
            Place("Bencab Museum", "It is a great place for art lovers to visit, it has a variety of art collections and art pieces. It has a nice garden and there is an opportunity for an eco-trail walk. The inside staff are accommodating, they would offer to have your pictures taken.", 16.4107, 120.5504)
        )
        ),

        UserMap("Burnham Park",
        listOf(
            Place("Burnham Park", "Burnham Park is the most famous attraction in the city. It is dominated by the Burnham Lake (or Lagoon) and dotted with other interesting spots like the Rose Garden, a recreational park with a skating rink, the Orchidarium, the Children’s Park, and the Melvin Jones Grandstand.", 16.4114, 120.5940)
        )
        ),

        UserMap("Baguio Museum",
        listOf(
            Place("Baguio Museum", "The museum’s collections tell the history of the city and showcase the culture of the Cordillera region. The four floors host all the collections classified into categories — Baguio Gallery, Cordillera Gallery, and the Alternative Gallery.", 16.4070, 120.5984)
        )
        ),
        UserMap("MT.Yangbew",
        listOf(
            Place("Mt.Yangbew", "Mount Yangbew is one of the locals’ favorite hiking, camping or sunrise/sunset viewing near Baguio. It is also known as Mt Jumbo or Mt Jambo because it used to be the place for the National Scout Jamboree of the Boy Scouts of America when the Americans were in the region decades ago.", 16.4540, 120.6071)
        )
        ),

        UserMap("Mt.Kalugong",
        listOf(
            Place("Mt.Kalugong", "Mount Kalugong is known for their famous limestone rock formations. The trail going there might be easy but climbing rocks is not for the faint of heart. You’ll catch a bird’s eye view of La Trinidad valley and see beautiful limestone rock formations, pine forests, and endemic wildlife. Mount Kalugong is named as such because it resembles the shape of a hat if viewed from the town center.", 16.4589, 120.5961)
        )
        ),
        UserMap("Baguio Night Market",
        listOf(
            Place("Baguio Night Market", "Baguio is developing a strong night market culture, drawing tourists to the area along Harrison Road in the Central Business District. This is perfect for those who want to score bargain items or fill their tummies without digging deeper into their pockets.", 16.413082, 120.595062)
        )
        )
        )

    }

}