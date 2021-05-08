package com.example.jeepitybasic

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import android.app.Activity
import android.content.IntentSender
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * DISPLAY MAP ACTIVITY.
 */
// [START maps_marker_on_map_ready]
private const val TAG = "DisplayMapActivity"


class MapsMarkerActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val REQUEST_CHECK_SETTINGS = 43
    }

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
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
            //    val latitude: Double = Location.getLatitude()
              //  val longitude: Double = Location.getLongitude()
            }




    }


    private fun getLocationUpdates() {
        locationRequest = LocationRequest()
        //locationRequest.interval = 30000
       // locationRequest.fastestInterval = 20000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        println("got this far 1")

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {   println("got this far 2")
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    println("got this far 3")


                    lateinit var databaseRef: DatabaseReference
                 databaseRef = Firebase.database.reference
                    val locationlogging = LocationLogging(location.latitude, location.longitude)
                    databaseRef.child("userlocation").setValue(locationlogging)
                        .addOnSuccessListener {
                            Toast.makeText(applicationContext, "Locations written into the database", Toast.LENGTH_LONG).show()
                            println("got this far 4")
                        }
                        .addOnFailureListener {
                            Toast.makeText(applicationContext, "Error occured while writing the locations", Toast.LENGTH_LONG).show()
                       println("got this far 5")
                        }


/* To update only one attribute, use the following statement :
                   database.child("userslocation").child("Latitude").setValue(location.latitude) */


                    if (location != null) {
                        println("got this far 6")
                        val latLng = LatLng(location.latitude, location.longitude)
                        val markerOptions = MarkerOptions().position(latLng)
                        mMap.addMarker(markerOptions)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    }
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
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isZoomControlsEnabled = true
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
        grantResults: IntArray,
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