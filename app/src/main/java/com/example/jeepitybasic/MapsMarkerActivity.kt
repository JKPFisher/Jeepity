package com.example.jeepitybasic


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.jeepitybasic.models.UserMap
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*
import java.util.*

/**
 * DISPLAY MAP ACTIVITY.
 */
// [START maps_marker_on_map_ready]
private const val TAG = "DisplayMapActivity"


class MapsMarkerActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val REQUEST_CHECK_SETTINGS = 43
    }
    lateinit var databaseRef: DatabaseReference
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


        databaseRef = Firebase.database.reference
        databaseRef.addValueEventListener(logListener)


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




        val pins: Button = findViewById<Button>(R.id.pins)
        pins.setOnClickListener { val intent = Intent(this,
            Home::class.java)
            mMap.clear()
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

        //This code is to pass the value to Fragment

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val myFragment = BottomSheetFragment()


         refresh.setOnClickListener {





                   if (spinner_destination.selectedItem.toString().equals(spinner_location.selectedItem.toString())
                   ) {
                       Text.text = ("Take the incoming jeepney of barangay " + spinner_destination.selectedItem.toString())
                       text2.text = (" ")



                   }
                     if ((spinner_destination.selectedItem.toString().equals("Legarda-Burnham-Kisad"))
                     ) {

                         Text.text = ("Take the outgoing jeepney of barangay " + spinner_location.selectedItem.toString())
                         text2.text = (" ")




                     }else {
                       when (spinner_location.selectedItem.toString()) {
                           ("A. Bonifacio-Caguioa-Rimando") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Abanao-Zandueta-Kayong-Chugum-Otek") -> Text.text = "Take the "

                           ("Alfonso Tabora") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan of "

                           ("Ambiong") -> Text.text = "Take the Ambiong Jeep to the paradahan of"

                           ("Andres Boni facio (Lower Bokawkan)") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Apugan-Loakan") -> Text.text =
                               "Take the Loakan Jeep to the paradahan of"

                           ("Asin Road") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Atok Trail") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Aurora Hill Proper (Malvar-Sgt. Floresca)") -> Text.text =
                               "Take the Aurora Hill Jeep to the paradahan of"

                           ("Aurora Hill, North Central") -> Text.text =
                               "Take the Aurora Hill  Jeep to the paradahan of"

                           ("Aurora Hill, South Central") -> Text.text =
                               "Take the Aurora Hill  Jeep to the paradahan of"

//("Bagong Lipunan (Market Area)") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Bakakeng Central") -> Text.text =
                               "Take the Bakakeng Jeep to the paradahan of"

                           ("Bakakeng North") -> Text.text =
                               "Take the Bakakeng Jeep to the paradahan of"

//("Bal-Marcoville (Marcoville)") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Balsigan") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Bayan Park East") -> Text.text =
                               "Take the Aurora Hill or Leonila Hill Jeep to the paradahan of"

                           ("Bayan Park Village") -> Text.text =
                               "Take the Aurora Hill or Leonila Hill Jeep to the paradahan of"

                           ("Bayan Park West (Bayan Park, Leonila Hill)") -> Text.text =
                               "Take the Aurora Hill or Leonila Hill Jeep to the paradahan of"

                           ("BGH Compound") -> Text.text =
                               "Take the BGH - Campo Sioco Jeep to the paradahan of"

                           ("Brookside") -> Text.text =
                               "Take the Brookside  Jeep to the paradahan of"

//("Brookspoint") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Cabinet Hill-Teacher's Camp") -> Text.text =
                               "Take the Mines View Jeep to the paradahan of"

                           ("Camdas Subdivision") -> Text.text =
                               "Take the Trancoville Jeep to the paradahan of"

                           ("Camp 7") -> Text.text = "Take the Camp 7 Jeep to the paradahan of"

                           ("Camp 8") -> Text.text = "Take the Camp 8 Jeep to the paradahan of"

                           ("Camp Allen") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

//("Campo Filipino") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("City Camp Central") -> Text.text =
                               "Take the City Camp Jeep to the paradahan of"

                           ("City Camp Proper") -> Text.text =
                               "Take the City Camp Jeep to the paradahan of"

                           ("Country Club Village") -> Text.text =
                               "Take the Country Club Jeep to the paradahan of"

//("Cresencia Village") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Dagsian, Lower") -> Text.text =
                               "Take the Dagsian Jeep to the paradahan of"

                           ("Dagsian, Upper") -> Text.text =
                               "Take the Dagsian Jeep to the paradahan of"

//("Dizon Subdivision") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Dominican Hill-Mirador") -> Text.text =
                               "Take the Lourdes Jeep to the paradahan of"

//("Dontogan") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

//("DPS Compound") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

//("Engineers' Hill") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

//("Esmeralda Avenue") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Fairview Village") -> Text.text =
                               "Take the Fairview Jeep to the paradahan of"

                           ("Ferdinand (Happy Homes-Campo Sioco)") -> Text.text =
                               "Take the Campo Sioco Jeep to the paradahan of"

//("Fort del Pilar") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Gabriela Silang") -> Text.text =
                               "Take the Gabriela Silang Jeep to the paradahan of"

                           ("General Emilio F. Aguinaldo (QuirinoMagsaysay, Lower)") -> Text.text =
                               "Take the City Camp (QM) Jeep to the paradahan of"

//("General Luna, Upper") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

//("General Luna, Lower") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Gibraltar") -> Text.text =
                               "Take the Mines View Jeep to the paradahan of"

                           ("Greenwater Village") -> Text.text =
                               "Take the Greenwater Jeep to the paradahan of"

                           ("Guisad Central") -> Text.text =
                               "Take the Guisad Jeep to the paradahan of"

                           ("Guisad Sorong") -> Text.text =
                               "Take the Guisad Jeep to the paradahan of"

                           ("Happy Hollow") -> Text.text =
                               "Take the Happy Hollow  Jeep to the paradahan of"

//("Happy Homes (Happy Homes-Lucban)”) -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the par
                       }




                       when (spinner_destination.selectedItem.toString()) {
                           ("Legarda-Burnham-Kisad") -> text2.text =
                               " "
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
                               "Gabriela Silang, located at 546-526 Gabriela Silang road"

                       }

                   }
             val bundle = Bundle()
             bundle.putString("message", Text.text.toString())
             myFragment.arguments = bundle
             fragmentTransaction.add(R.id.frameLayout, myFragment).commit()
               }

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



                 databaseRef = Firebase.database.reference
                    val locationlogging = LocationLogging(location.latitude, location.longitude)
                    databaseRef.child("userlocation").setValue(locationlogging)
                        .addOnSuccessListener {
                            Toast.makeText(applicationContext,
                                "Locations written into the database",
                                Toast.LENGTH_LONG).show()
                            println("got this far 4")
                        }
                        .addOnFailureListener {
                            Toast.makeText(applicationContext,
                                "Error occured while writing the locations",
                                Toast.LENGTH_LONG).show()
                       println("got this far 5")
                        }


/* To update only one attribute, use the following statement :
                   database.child("userslocation").child("Latitude").setValue(location.latitude)


                    if (location != null) {
                        println("got this far 6")
                        val latLng = LatLng(location.latitude, location.longitude)
                        val markerOptions = MarkerOptions().position(latLng)
                        mMap.addMarker(markerOptions)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))


                    }*/
                }
            }
        }
    }

    val logListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            TODO("Could not read from database")
            //flash error message?
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            println("got this far 7")
            if (dataSnapshot.exists()) {
                val locationlogging = dataSnapshot.child("userlocation").getValue(LocationLogging::class.java)
                var Lat=locationlogging?.Latitude
                var Long=locationlogging?.Longitude


                val geocoder: Geocoder
                val addresses: List<Address>
                geocoder = Geocoder(applicationContext, Locale.getDefault())
                //Log.d("Latitude of driver", driverLat.toString())
                //    Log.d("Longitude read from database", driverLong.toString())

                if (Lat !=null  && Long != null) {
                    addresses = geocoder.getFromLocation(Lat, Long, 15)
                    val address: String = addresses[0].getAddressLine(0)
                    textViewUserLoc.text=address


                    //*************************************************************************
                    val spinner_location: Spinner = findViewById(R.id.spinner)
                    when {
                        address.contains("SLU Hospital Drwy") ->  spinner_location.setSelection(0)
                        address.contains("Upper Bonifacio St") ->  spinner_location.setSelection(0)
                        address.contains("Dagohoy St") ->  spinner_location.setSelection(0)
//UPDATE

                    }
                    //      textViewUserLoc.text="Latitude:"+driverLat.toString()+", Longitude: "+driverLong.toString()
                    println("got this far 8")

                    val driverLoc = LatLng(Lat, Long)
                    val markerOptions = MarkerOptions().position(driverLoc).title("Your Location")
                    mMap.addMarker(markerOptions)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(driverLoc, 10f))
                    // 1: World, 5: Landmass/continent, 10: City, 15: Streets and 20: Buildings
                    Toast.makeText(
                        applicationContext,
                        "Locations accessed from the database",
                        Toast.LENGTH_LONG
                    ).show()
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

           val spinner_destination: Spinner = findViewById(R.id.spinner2)
           when {
              userMap.title.equals("Igorot Park") -> spinner_destination.setSelection(66)


           }
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