package com.example.jeepitybasic

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.common.api.GoogleApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_route.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*
import java.io.IOException

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_fragment, container, false)


    }







        fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
            parent.getItemAtPosition(pos)
            // Get Selected Class name from the list


        }
        fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }



/*

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.barangays,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }


        // Create an ArrayAdapter using the string array and a default spinner layout
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.barangays,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner2.adapter = adapter

                button1.setOnClickListener {
                    println("got this far 1")
                    if (spinner2.selectedItem.toString().equals(spinner.selectedItem.toString())
                    ) {
                        println("got this far 2")
                        textView10.text =
                            ("Take the incoming jeepney of barangay " + spinner2.selectedItem.toString())
                        textView11.text = (" ")

                    } else {
                        println("got this far 3")
                        when (spinner.selectedItem.toString()) {
                            ("SLU-SVP Housing Village") -> textView10.text =
                                "Take the Bakakeng Jeep to the paradahan of"

                            ("A. Bonifacio-Caguioa-Rimando") -> textView11.text =
                                "Take the Aurora Hill or Trancoville Jeep to the paradahan of"


                        }




                        when (spinner2.selectedItem.toString()) {
                            ("Aurora Hill Proper") -> textView11.text =
                                "Aurora Hill, located at Harrison road. Then Take the Aurora Hill Jeep."

                        }
                    }


                }


            }
        }
    }*/
    }






