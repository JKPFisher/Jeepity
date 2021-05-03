package com.example.jeepitybasic

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.jeepitybasic.models.UserMap
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import java.net.HttpURLConnection
import java.net.URL

/**
 * DISPLAY MAP ACTIVITY.
 */
// [START maps_marker_on_map_ready]
private const val TAG = "DisplayMapActivity"


class MapsMarkerActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var userMap: UserMap
    private val LOCATION_PERMISSION_REQUEST = 1
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var address: Address
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        userMap = intent.getSerializableExtra(EXTRA_USER_MAP) as UserMap
        userMap = intent.getSerializableExtra(EXTRA_USER_MAP2) as UserMap
        userMap = intent.getSerializableExtra(EXTRA_USER_MAP3) as UserMap
        userMap = intent.getSerializableExtra(EXTRA_USER_MAP4) as UserMap
        if (getString(R.string.maps_api_key).isEmpty()) {
            Toast.makeText(this,
                "Add your own API key in MapWithMarker/app/secure.properties as MAPS_API_KEY=YOUR_API_KEY",
                Toast.LENGTH_LONG).show()
        }


        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)



        //**********************************************************
        val Text: TextView = findViewById<TextView>(R.id.textView3)
        val text2: TextView = findViewById<TextView>(R.id.textView4)


        val refresh: Button = findViewById<Button>(R.id.btn_show)

        val bottomSheetFragment = BottomSheetFragment()
        refresh.setOnClickListener {bottomSheetFragment.show(supportFragmentManager,
            "BottomSheetDialog")}

        val offline: Button = findViewById<Button>(R.id.offline)
        offline.setOnClickListener { val intent = Intent(this,
            OfflineRoutes::class.java)
            startActivity(intent)}

        val pins: Button = findViewById<Button>(R.id.pins)
        pins.setOnClickListener { val intent = Intent(this,
            SavedLocations::class.java)
            startActivity(intent)}






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
           // val spinner_destination = findViewById<View>(R.id.spinner2) as Spinner
          //  val spinner_location = findViewById<View>(R.id.spinner) as Spinner





/*
          refresh.setOnClickListener {

                    if (spinner_destination.selectedItem.toString().equals(spinner_location.selectedItem.toString())
                    ) {
                        Text.text = ("Take the incoming jeepney of barangay " + spinner_destination.selectedItem.toString())
                        text2.text = (" ")

                    } else {
                        when (spinner_location.selectedItem.toString()) {
                            ("SLU-SVP Housing Village") -> Text.text =
                                "Take the Bakakeng Jeep to the paradahan of"

                            ("A. Bonifacio-Caguioa-Rimando") -> Text.text =
                                "Take the Aurora Hill or Trancoville Jeep to the paradahan of"


                        }




                        when (spinner_destination.selectedItem.toString()) {
                            ("Aurora Hill Proper") -> text2.text =
                                "Aurora Hill, located at Harrison road. Then Take the Aurora Hill Jeep."

                        }

                    }
                } */


            //user_location.onItemSelectedListener = null


        } //AFTER THIS LINE
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
            //    val latitude: Double = Location.getLatitude()
              //  val longitude: Double = Location.getLongitude()
            }




    }


    private fun getLocationUpdates() {


        locationRequest = LocationRequest()
        locationRequest.interval = 30000
        locationRequest.fastestInterval = 20000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {

                fun concat(two: String, one: String) {

                }
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    mMap.clear();
                    if (location != null) {

                        fun getAdminArea(){
                     //      address=Address(getAdminArea())
                            //administative area level 5
                            val add = address
                        }
                        val latLng = LatLng(location.latitude, location.longitude)

                        println("location recalibrated")

                        val markerOptions = MarkerOptions().position(latLng).title("Your Location").snippet("Your Coordinates are: "+location.latitude +location.longitude)
                        mMap.addMarker(markerOptions)

                        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

                    } //Now that we've extracted latitude and longitude, we need google to give us an address !!
                }
            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    // [END maps_marker_get_map_async]
    // [END_EXCLUDE]

    // [START maps_marker_on_map_ready_add_marker]

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        getLocationAccess()

      Log.i(TAG, "user map to render: ${userMap.title}")
     val boundsBuilder = LatLngBounds.builder()
       for (place in userMap.places){
          val latLng = LatLng(place.latitude, place.longitude)
          boundsBuilder.include(latLng)
           mMap.addMarker(MarkerOptions().position(latLng).title(place.title)
               .snippet(place.description))
       }
       googleMap.apply {
            //val baguio = LatLng(16.402434166768064, 120.59588659538619)

          //  mMap.addMarker(MarkerOptions().position(baguio).title("Baguio"))
            // [START_EXCLUDE silent]
           mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),
               1000,
               1000,
               0))


          ///  mMap.setMinZoomPreference(11.0f)
           // mMap.setMaxZoomPreference(20.0f)

            // [END_EXCLUDE]
       }

    }
    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
            getLocationUpdates()
            startLocationUpdates()
        }
        else
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                )
                mMap.isMyLocationEnabled = true

            }
            else {
                Toast.makeText(this,
                    "User has not granted location access permission",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

}
// [END maps_marker_on_map_ready]