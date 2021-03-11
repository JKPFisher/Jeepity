
package com.example.jeepitybasic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val offline: Button = findViewById<Button>(R.id.offline)
        val Text: TextView = findViewById<TextView>(R.id.textView3)
        val text2: TextView = findViewById<TextView>(R.id.textView4)
        val refresh: Button = findViewById<Button>(R.id.button)

        offline.setOnClickListener {
            val intent = Intent(this, OfflineRoutes::class.java)
            startActivity(intent)
        }


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
/*@Test SAMPLE FOR COMBINING TWO WHEN CASES
            fun testCaseCombination() {
                val fileType = UnixFileType.D

                val frequentFileType: Boolean = when (fileType) {
                    UnixFileType.HYPHEN_MINUS, UnixFileType.D -> true
                    else -> false
                }

                assertTrue(frequentFileType)
            }*/
            val spinner_location = findViewById<View>(R.id.spinner) as Spinner

            refresh.setOnClickListener {
                when (spinner_location.selectedItem.toString()) {
                    ("SLU-SVP Housing Village") -> Text.text =
                        "Take the Bakakeng Jeep to the paradahan of"

                    ("A. Bonifacio-Caguioa-Rimando") -> Text.text =
                        "Take the Aurora Hill or Trancoville Jeep to the paradahan of"
                }


                val spinner_destination = findViewById<View>(R.id.spinner2) as Spinner


                    when (spinner_destination.selectedItem.toString()) {
                        ("Aurora Hill Proper") -> text2.text =
                            "Aurora Hill, located at Harrison road. Then Take the Aurora Hill Jeep"

                }


            }

            //user_location.onItemSelectedListener = null


        }
    }
}


