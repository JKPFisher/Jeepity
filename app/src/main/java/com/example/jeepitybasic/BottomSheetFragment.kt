package com.example.jeepitybasic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_fragment.*


public class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var Justwork: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View? {


        val view: View = inflater.inflate(R.layout.bottomsheet_fragment, container, false)


        Justwork = view.findViewById<View>(R.id.textView5) as TextView
        val bundle = arguments
        val message = bundle!!.getString("message")
        Justwork.text = message
        return view
    }
   // fun getBundle(key: String?): Bundle?{}


        fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
            parent.getItemAtPosition(pos)
            // Get Selected Class name from the list

        }
        fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback

        }


    }





