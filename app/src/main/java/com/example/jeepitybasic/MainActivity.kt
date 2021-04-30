/*

package com.example.jeepitybasic

import android.content.Intent
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

//class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val offline: Button = findViewById<Button>(R.id.offline)
        val Text: TextView = findViewById<TextView>(R.id.textView3)
        val text2: TextView = findViewById<TextView>(R.id.textView4)
        val refresh: Button = findViewById<Button>(R.id.button)

      //  offline.setOnClickListener {
     //       val intent = Intent(this, OfflineRoutes::class.java)
      //      startActivity(intent)
     //   }
     //   offline.setOnClickListener {
    //        val intent = Intent(this@MainActivity, MapsMarkerActivity::class.java)
    //        startActivity(intent)
    //    }


        val spinner_location: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.barangays,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_location.adapter = adapter
        }

        val spinner_destination: Spinner = findViewById(R.id.spinner2)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.barangays,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_destination.adapter = adapter



            fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                parent.getItemAtPosition(pos)
                // Get Selected Class name from the list


            }

            fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
*/
/*@Test SAMPLE FOR COMBINING TWO WHEN CASES
            fun testCaseCombination() {
                val fileType = UnixFileType.D

                val frequentFileType: Boolean = when (fileType) {
                    UnixFileType.HYPHEN_MINUS, UnixFileType.D -> true
                    else -> false
                }

                assertTrue(frequentFileType)
            }*//*

            val spinner_location = findViewById<View>(R.id.spinner) as Spinner

            refresh.setOnClickListener {
                when (spinner_location.selectedItem.toString()) {
                    ("SLU-SVP Housing Village") -> Text.text =
                        "Take the Bakakeng Jeep to the paradahan of"

                    ("A. Bonifacio-Caguioa-Rimando") -> Text.text =
                        "Take the Aurora Hill or Trancoville Jeep to the paradahan of"
                    ("A. Bonifacio-Caguioa-Rimando") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                        ("Abanao-Zandueta-Kayong-Chugum-Otek") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                        ("Alfonso Tabora") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                    ("Ambiong") -> Text.text = "Take the Ambiong Jeep to the paradahan of"
                        ("Andres Boni facio (Lower Bokawkan)") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Apugan-Loakan") -> Text.text = "Take the Loakan Jeep to the paradahan of"

                            ("Asin Road") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Atok Trail") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Aurora Hill Proper (Malvar-Sgt. Floresca)") -> Text.text = "Take the Aurora Hill Jeep to the paradahan of"

                            ("Aurora Hill, North Central") -> Text.text = "Take the Aurora Hill  Jeep to the paradahan of"

                            ("Aurora Hill, South Central") -> Text.text = "Take the Aurora Hill  Jeep to the paradahan of"

                                //("Bagong Lipunan (Market Area)") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Bakakeng Central") -> Text.text = "Take the Bakakeng Jeep to the paradahan of"

                            ("Bakakeng North") -> Text.text = "Take the Bakakeng Jeep to the paradahan of"

                                //("Bal-Marcoville (Marcoville)”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Balsigan") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Bayan Park East") -> Text.text = "Take the Aurora Hill or Leonila Hill Jeep to the paradahan of"

                            ("Bayan Park Village") -> Text.text = "Take the Aurora Hill or Leonila Hill Jeep to the paradahan of"

                            ("Bayan Park West (Bayan Park, Leonila Hill)") -> Text.text = "Take the Aurora Hill or Leonila Hill Jeep to the paradahan of"

                            ("BGH Compound") -> Text.text = "Take the BGH - Campo Sioco Jeep to the paradahan of"

                            ("Brookside") -> Text.text = "Take the Brookside  Jeep to the paradahan of"

                               // *("Brookspoint”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Cabinet Hill-Teacher's Camp") -> Text.text = "Take the Mines View Jeep to the paradahan of"

                            ("Camdas Subdivision") -> Text.text = "Take the Trancoville Jeep to the paradahan of"

                            ("Camp 7") -> Text.text = "Take the Camp 7 Jeep to the paradahan of"

                            ("Camp 8") -> Text.text = "Take the Camp 8 Jeep to the paradahan of"

                            ("Camp Allen") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Campo Filipino”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("City Camp Central") -> Text.text = "Take the City Camp Jeep to the paradahan of"

                            ("City Camp Proper") -> Text.text = "Take the City Camp Jeep to the paradahan of"

                            ("Country Club Village") -> Text.text = "Take the Country Club Jeep to the paradahan of"

                             //   *("Cresencia Village”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Dagsian, Lower") -> Text.text = "Take the Dagsian Jeep to the paradahan of"

                            ("Dagsian, Upper") -> Text.text = "Take the Dagsian Jeep to the paradahan of"

                               // *("Dizon Subdivision”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Dominican Hill-Mirador”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Dontogan”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("DPS Compound”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Engineers' Hill”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Esmeralda Avenue”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Fairview Village") -> Text.text = "Take the Fairview Jeep to the paradahan of"

                            ("Ferdinand (Happy Homes-Campo Sioco)") -> Text.text = "Take the Campo Sioco Jeep to the paradahan of"

                               // *("Fort del Pilar”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Gabriela Silang") -> Text.text = "Take the Gabriela Silang Jeep to the paradahan of"

                            ("General Emilio F. Aguinaldo (QuirinoMagsaysay, Lower)") -> Text.text = "Take the City Camp (QM) Jeep to the paradahan of"

                               // *("General Luna, Upper”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("General Luna, Lower”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Gibraltar") -> Text.text = "Take the Mines View Jeep to the paradahan of"

                            ("Greenwater Village") -> Text.text = "Take the Greenwater Jeep to the paradahan of"

                            ("Guisad Central") -> Text.text = "Take the Guisad Jeep to the paradahan of"

                            ("Guisad Sorong") -> Text.text = "Take the Guisad Jeep to the paradahan of"

                            ("Happy Hollow") -> Text.text = "Take the Happy Hollow  Jeep to the paradahan of"

                              //  *("Happy Homes (Happy Homes-Lucban)”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Harrison-Claudio Carantes”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Hillside") -> Text.text = "Take the Hillside Jeep to the paradahan of"

                            ("Holy Ghost Extension") -> Text.text = "Take the Holy Ghost Jeep to the paradahan of"

                            ("Holy Ghost Proper") -> Text.text = "Take the Holy Ghost Jeep to the paradahan of"

                            ("Honeymoon (Honeymoon-Holy Ghost)") -> Text.text = "Take the Holy Ghost Jeep to the paradahan of"

                            ("Imelda R. Marcos (La Salle)") -> Text.text = "Take the Campo Sioco Jeep to the paradahan of"

                            ("Imelda Village") -> Text.text = "Take the Campo Sioco Jeep to the paradahan of"

                            ("Irisan") -> Text.text = "Take the Irisan Jeep to the paradahan of"

                              //  *("Kabayanihan”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Kagitingan”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Kayang Extension”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Kayang-Hilltop”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Kias") -> Text.text = "Take the PMA -Kias  Jeep to the paradahan of"

                               // *("Legarda-Burnham-Kisad”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Liwanag-Loakan") -> Text.text = "Take the Loakan Jeep to the paradahan of"

                            ("Loakan Proper") -> Text.text = "Take the Loakan Jeep to the paradahan of"

                               // *("Lopez Jaena”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Lourdes Subdivision Extension") -> Text.text = "Take the City Camp (Quarry) Jeep to the paradahan of"

                            ("Lourdes Subdivision, Lower") -> Text.text = "Take the City Camp (Quarry) Jeep to the paradahan of"

                            ("Lourdes Subdivision, Proper") -> Text.text = "Take the City Camp (Quarry) Jeep to the paradahan of"

                               // *("Lualhati”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Lucnab”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Magsaysay Private Road") -> Text.text = "Take the Trancoville Jeep to the paradahan of"

                            ("Magsaysay, Lower") -> Text.text = "Take the Trancoville Jeep to the paradahan of"

                            ("Magsaysay, Upper") -> Text.text = "Take the Trancoville Jeep to the paradahan of"

                             //   *("Malcolm Square-Perfecto (Jose Abad Santos)”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Manuel A. Roxas”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Market Subdivision, Upper”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Middle Quezon Hill Subdivision (Quezon Hill Middle)") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"

                            ("Military Cut-off") -> Text.text = "Take the Military Cut-off Jeep to the paradahan of"

                            ("Mines View Park") -> Text.text = "Take the Mines View Jeep to the paradahan of"

                               // *("Modern Site, East”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Modern Site, West”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("MRR-Queen of Peace") -> Text.text = "Take the City Camp (Quarry)  Jeep to the paradahan of"

                               // *("New Lucban”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Outlook Drive”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Pacdal”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Padre Burgos”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Padre Zamora”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                             //   *("Palma-Urbano (Cariño-Palma)”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Kias") -> Text.text = "Take the PMA-Kias Jeep to the paradahan of"

                            ("Phil-Am") -> Text.text = "Take the Campo Sioco Jeep to the paradahan of"

                            ("Pinget") -> Text.text = "Take the Pinget Jeep to the paradahan of"

                               // *("Pinsao Pilot Project”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                               // *("Pinsao Proper”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Poliwes") -> Text.text = "Take the Poliwes Jeep to the paradahan of"

                               // *("Pucsusan”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Quezon Hill Proper") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"

                            ("Quezon Hill, Upper") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"

                            ("Quirino Hill, East") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"

                            ("Quirino Hill, Lower") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"

                            ("Quirino Hill, Middle") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"

                            ("Quirino Hill, West") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"

                            ("Quirino-Magsaysay, Upper (Upper QM)") -> Text.text = "Take the City Camp (QM )Jeep to the paradahan of"

                              //  *("Rizal Monument Area”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Rock Quarry, Lower") -> Text.text = "Take the City Camp (QM) Jeep to the paradahan of"

                            ("Rock Quarry, Middle") -> Text.text = "Take the City Camp (QM) Jeep to the paradahan of"

                            ("Rock Quarry, Upper") -> Text.text = "Take the City Camp (QM) Jeep to the paradahan of"

                            //    *("Saint Joseph Village”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           //     *("Salud Mitra”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                             //   *("San Antonio Village”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("San Luis Village”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                             //   *("San Roque Village”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("San Vicente") -> Text.text = "Take the San Vicente Jeep to the paradahan of"

                              //  *("Sanitary Camp, North”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Sanitary Camp, South”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                             //   *("Santa Escolastica”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Santo Rosario")-> Text.text = "Take the Campo Sioco Jeep to the paradahan of"

                              //  *("Santo Tomas Proper”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                              //  *("Santo Tomas School Area”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                            ("Scout Barrio") -> Text.text = "Take the Scout Barrio Jeep to the paradahan of"

                             ("Slaughter House Area (Santo Niño Slaughter)") -> Text.text = "Take the Trancoville Jeep to the paradahan of"

                            ("SLU-SVP Housing Village") -> Text.text = "Take the Bakakeng Jeep to the paradahan of"

                        //*("South Drive”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                         //   *("Teodora Alonzo”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                        ("Trancoville") -> Text.text = "Take the Trancoville Jeep to the paradahan of"

                        ("Victoria Village") -> Text.text = "Take the Quezon Hill Jeep to the paradahan of"
                }


                val spinner_destination = findViewById<View>(R.id.spinner2) as Spinner


                    when (spinner_destination.selectedItem.toString()) {
                        ("Aurora Hill Proper") -> text2.text =
                            "Aurora Hill, located at Harrison road. Then Take the Aurora Hill Jeep"
                        ("Trancoville ") -> text2.text =
                            "Trancoville , located at Rambakan Dr road or Harrison road . Then Take the Trancoville Jeep"
                        ("Loakan") -> text2.text =
                            "Loakan, located at Mabini street. Then Take the Loakan Jeep"
                        ("Atok Mines") -> text2.text =
                            "Atok Mines, located at Klantiao Street. Then Take the Atok Mines Jeep"
                        ("Baguio-Itogon") -> text2.text =
                            "Baguio-Itogon, located at Klantiao Street. Then Take the Baguio-Itogon Jeep"
                        ("Baguio-La Trinidad") -> text2.text =
                            "Baguio-La Trinidad, located at Magsaysay Avenue  road. Then Take the Baguio-La Trinidad Jeep"
                        ("Irisan") -> text2.text =
                            "Irisan, located at Shanum Street. Then Take the Irisan Jeep"
                        ("Camp 7") -> text2.text =
                            "Camp 7, located at Harrison road. Then Take the Camp 7 Jeep"
                        ("Guisad/Ferguson/Lourdes Subd.") -> text2.text =
                            "Guisad/Ferguson/Lourdes Subd., located at Kayang Street. Then Take the Guisad/Ferguson/Lourdes Subd. Jeep"
                        ("Quezon Hill") -> text2.text =
                            "Quezon Hill, located at #9 Estigoy Building Hiltop Kayang Street. Then Take the Quezon Hill Jeep"
                        ("Baguio-Sablan") -> text2.text =
                            "Baguio-Sablan, located at KG Baguio, 10 Kayang Street. Then Take the Baguio-Sablan Jeep"
                        ("Baguio-Bayabas") -> text2.text =
                            "Baguio-Bayabas, located at Upper P. Burgos. Then Take the Baguio-Bayabas Jeep"
                        ("Acupan ") -> text2.text =
                            "Acupan , located at 66th Infantry Park, F. Calderon Street. Then Take the Acupan Jeep"
                        ("Pinsao & San Carlos") -> text2.text =
                            "Pinsao & San Carlos, located at Kayang Street. Then Take the Pinsao & San Carlos Jeep"
                        ("San Vicente") -> text2.text =
                            "San Vicente, located at Balsigan road. Then Take the San Vicente Jeep"
                        ("Samoyao ") -> text2.text =
                            "Samoyao , located at Lapu Lapu Street. Then Take the Samoyao Jeep"
                        ("Philex") -> text2.text =
                            "Philex, located at Harrison road. Then Take the Philex Jeep"
                        ("Baguio-Shilan-Acop") -> text2.text =
                            "Baguio-Shilan-Acop, located at 16 Hilltop Street. Then Take the Baguio-Shilan-Acop Jeep"
                        ("Military Cut Off") -> text2.text =
                            "Military Cut Off, located at Perfecto Street. Then Take the Military Cut Off Jeep"
                        ("Gabriela Silang ") -> text2.text =
                            "Gabriela Silang , located at Shanum Street. Then Take the Gabriela Silang  Jeep"
                        ("Campo Sioco") -> text2.text =
                            "Campo Sioco, located at Hotel Veniz Building, Harrison Rd. Then Take the Campo Sioco Jeep"
                        ("Ambiong ") -> text2.text =
                            "Ambiong , located at 8 Lapu Lapu Street. Then Take the Ambiong Jeep"
                        ("Palma-Urbano") -> text2.text =
                            "Palma-Urbano, located at Otek Street. Then Take the Palma-Urbano Jeep"
                        ("") -> text2.text =
                            ", located at. Then Take the Jeep"
                        (" Crystal Cave") -> text2.text =
                            "Crystal Cave, located at Otek Street. Then Take the Crystal Cave Jeep"
                        ("Greenwater") -> text2.text =
                            "Greenwater, located at Perfecto Street. Then Take the Greenwater Jeep"
                        ("Campo Sioco / BGH") -> text2.text =
                            "Campo Sioco / BGH, located at Perfecto Street. Then Take the Campo Sioco / BGH Jeep"
                        ("PMA/Kias") -> text2.text =
                            "PMA/Kias, located at Perfecto Street. Then Take the PMA/Kias Jeep"
                        (" Bakakeng") -> text2.text =
                            "Bakakeng, located at Perfecto Street. Then Take the Bakakeng Jeep"
                        ("Ucab") -> text2.text =
                            "Ucab, located at Rajah Soliman Street. Then Take the Ucab Jeep"
                        ("PMA") -> text2.text =
                            "PMA, located at Shanum Street. Then Take the PMA Jeep"
                        ("Tuba") -> text2.text =
                            "Tuba, located at Shanum Street. Then Take the Tuba Jeep"
                        ("La Trinidad") -> text2.text =
                            "La Trinidad, located at Shanum Street. Then Take the La Trinidad Jeep"
                        ("Maria Basa") -> text2.text =
                            "Maria Basa, located at 60-240 Maria Basa Street. Then Take the Maria Basa Jeep"

                        ("Baguio-Pico") -> text2.text =
                            "Baguio-Pico, located at 6 Rajah Matanda Street. Then Take the Baguio-Pico Jeep"
                        ("Kitma ") -> text2.text =
                            "Kitma , located at Harrison road. Then Take the Kitma  Jeep"
                        ("Gabriela Silang") -> text2.text =
                            "Gabriela Silang, located at 546-526 Gabriela Silang road. Then Take the Gabriela Silang Jeep"
                }


            }

            //user_location.onItemSelectedListener = null


        }
    }

}

    }



*/