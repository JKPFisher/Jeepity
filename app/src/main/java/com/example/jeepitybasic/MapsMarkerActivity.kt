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
import com.example.jeepitybasic.models.Place
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
import java.lang.Math.acos
import java.util.*
import kotlin.properties.Delegates

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
    private var dist by Delegates.notNull<Double>()


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
        pins.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            mMap.clear()
         this.finish()
            this.finishAffinity()


            println("pins")
            startActivity(intent)
            System.exit(0)}






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

                 if (refresh.isEnabled()){
                     refresh.setOnClickListener(null)

                    textView21.text = ("Estimated Fare: "+dist)


                 }
             if ((textView3 != null)){
                 refresh.isEnabled
             }
             if (refresh == null && spinner_destination != null){
                 mMap.clear()

             }



                   if (spinner_destination.selectedItem.toString().equals(spinner_location.selectedItem.toString())
                   ) {
                       Text.text = ("Take the incoming jeepney of barangay " + spinner_destination.selectedItem.toString())
                       text2.text = (" ")



                   }
                     if ((spinner_destination.selectedItem.toString().equals("Legarda-Burnham-Kisad"))
                     ) {

                         Text.text = ("Take the outgoing jeepney of barangay " + spinner_location.selectedItem.toString())
                         text2.text = (" ")}

                         if ((spinner_destination.selectedItem.toString().equals("Session Road Area"))
                         ) {
                          Text.text ="Take the Outgoing Jeep of barangay "+ spinner_location.selectedItem.toString()

                     }else {
                       when (spinner_location.selectedItem.toString()) {
                           ("A. Bonifacio-Caguioa-Rimando") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Abanao-Zandueta-Kayong-Chugum-Otek") -> Text.text = "Take the "

                           ("Alfonso Tabora") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan  "

                           ("Ambiong") -> Text.text = "Take the Ambiong Jeep to the paradahan "

                           ("Andres Boni facio (Lower Bokawkan)") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Apugan-Loakan") -> Text.text =
                               "Take the Loakan Jeep to the paradahan "

                           ("Asin Road") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Atok Trail") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Aurora Hill Proper (Malvar-Sgt. Floresca)") -> Text.text =
                               "Take the Aurora Hill Jeep to the paradahan "

                           ("Aurora Hill, North Central") -> Text.text =
                               "Take the Aurora Hill  Jeep to the paradahan "

                           ("Aurora Hill, South Central") -> Text.text =
                               "Take the Aurora Hill  Jeep to the paradahan "

//("Bagong Lipunan (Market Area)") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Bakakeng Central") -> Text.text =
                               "Take the Bakakeng Jeep to the paradahan "

                           ("Bakakeng North") -> Text.text =
                               "Take the Bakakeng Jeep to the paradahan "

//("Bal-Marcoville (Marcoville)") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Balsigan") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Bayan Park East") -> Text.text =
                               "Take the Aurora Hill or Leonila Hill Jeep to the paradahan "

                           ("Bayan Park Village") -> Text.text =
                               "Take the Aurora Hill or Leonila Hill Jeep to the paradahan "

                           ("Bayan Park West (Bayan Park, Leonila Hill)") -> Text.text =
                               "Take the Aurora Hill or Leonila Hill Jeep to the paradahan "

                           ("BGH Compound") -> Text.text =
                               "Take the BGH - Campo Sioco Jeep to the paradahan "

                           ("Brookside") -> Text.text =
                               "Take the Brookside  Jeep to the paradahan "

("Brookspoint") -> Text.text = "Take the Brookspoint Jeep to the paradahan "

                           ("Cabinet Hill-Teacher's Camp") -> Text.text =
                               "Take the Mines View Jeep to the paradahan "

                           ("Camdas Subdivision") -> Text.text =
                               "Take the Trancoville Jeep to the paradahan "

                           ("Camp 7") -> Text.text = "Take the Camp 7 Jeep to the paradahan "

                           ("Camp 8") -> Text.text = "Take the Camp 8 Jeep to the paradahan "

                           ("Camp Allen") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Campo Filipino") -> Text.text =
                               "Take the Campo Filipino Jeep to the paradahan "
                           ("Crystal Cave") -> Text.text =
                               "Take the Crystal Cave Jeep to the paradahan "
                           ("City Camp Central") -> Text.text =
                               "Take the City Camp Jeep to the paradahan "

                           ("City Camp Proper") -> Text.text =
                               "Take the City Camp Jeep to the paradahan "

                           ("Country Club Village") -> Text.text =
                               "Take the Country Club Jeep to the paradahan "

//("Cresencia Village") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Dagsian, Lower") -> Text.text =
                               "Take the Dagsian Jeep to the paradahan "

                           ("Dagsian, Upper") -> Text.text =
                               "Take the Dagsian Jeep to the paradahan "

//("Dizon Subdivision") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Dominican Hill-Mirador") -> Text.text =
                               "Take the Lourdes Jeep to the paradahan "

//("Dontogan") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan "

//("DPS Compound") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Engineers' Hill") -> Text.text =
                               "Take the Aurora Hill or Trancoville Jeep to the paradahan "

//("Esmeralda Avenue") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan "

                           ("Fairview Village") -> Text.text =
                               "Take the Fairview Jeep to the paradahan "

                           ("Ferdinand (Happy Homes-Campo Sioco)") -> Text.text =
                               "Take the Campo Sioco Jeep to the paradahan "

//("Fort del Pilar") -> Text.text = "Take the Aurora Hill or Trancoville Jeep to the paradahan of"

                           ("Gabriela Silang") -> Text.text =
                               "Take the Gabriela Silang Jeep to the paradahan "

                           ("General Emilio F. Aguinaldo (QuirinoMagsaysay, Lower)") -> Text.text =
                               "Take the City Camp (QM) Jeep to the paradahan "

                           ("General Luna, Upper") -> Text.text =
                               "Take any city bound jeep to the paradahan "

                           ("General Luna, Lower") -> Text.text =
                               "Take any city bound jeep to the paradahan "

                           ("Gibraltar") -> Text.text =
                               "Take the Mines View Jeep to the paradahan "

                           ("Greenwater Village") -> Text.text =
                               "Take the Greenwater Jeep to the paradahan "

                           ("Guisad Central") -> Text.text =
                               "Take the Guisad Jeep to the paradahan "

                           ("Guisad Sorong") -> Text.text =
                               "Take the Guisad Jeep to the paradahan "

                           ("Happy Hollow") -> Text.text =
                               "Take the Happy Hollow  Jeep to the paradahan "

                            ("Happy Homes (Happy Homes-Lucban)") -> Text.text =
                                "Take the Brookside Jeep to the paradahan"


                           ("Abanao-Zandueta-Kayong-Chugum-Otek") -> Text.text =
                               "Take any express Jeepney infront of Abano Square or walk"
                           ("Manuel A. Roxas") -> Text.text =
                               "Take the Trancoville Jeepney located along Harrison Rd."
                       }




                       when (spinner_destination.selectedItem.toString()) {
                           ("Legarda-Burnham-Kisad") -> text2.text =
                               " "
                           ("Aurora Hill Proper") -> text2.text =
                               "of Aurora Hill, located at Harrison road. Then Take the Aurora Hill Jeep"
                           ("Trancoville ") -> text2.text =
                               " of Trancoville , located at Rambakan Dr road or Harrison road . Then Take the Trancoville Jeep"
                           ("Loakan") -> text2.text =
                               " of Loakan, located at Mabini street. Then Take the Loakan Jeep"
                           ("Atok Mines") -> text2.text =
                               "of Atok Mines, located at Klantiao Street. Then Take the Atok Mines Jeep"
                           ("Baguio-Itogon") -> text2.text =
                               "of Baguio-Itogon, located at Klantiao Street. Then Take the Baguio-Itogon Jeep"
                           ("Baguio-La Trinidad") -> text2.text =
                               "of Baguio-La Trinidad, located at Magsaysay Avenue  road. Then Take the Baguio-La Trinidad Jeep"
                           ("Irisan") -> text2.text =
                               " of Irisan, located at Shanum Street. Then Take the Irisan Jeep"
                           ("Camp 7") -> text2.text =
                               "of Camp 7, located at Harrison road. Then Take the Camp 7 Jeep"
                           ("Lourdes Subdivision Extension") -> text2.text =
                               "of Guisad/Ferguson/Lourdes Subd., located at Kayang Street." +
                                       " Then Take the Guisad/Ferguson/Lourdes Subd. Jeep"
                           ("Lourdes Subdivision, Lower") -> text2.text =
                               "of Guisad/Ferguson/Lourdes Subd., located at Kayang Street." +
                                       " Then Take the Guisad/Ferguson/Lourdes Subd. Jeep"
                           ("Lourdes Subdivision, Proper") -> text2.text =
                               "of Guisad/Ferguson/Lourdes Subd., located at Kayang Street." +
                                       " Then Take the Guisad/Ferguson/Lourdes Subd. Jeep"
                           ("Guisad Central") -> text2.text =
                               "of Guisad/Ferguson/Lourdes Subd., located at Kayang Street." +
                                       " Then Take the Guisad/Ferguson/Lourdes Subd. Jeep"
                           ("Guisad/ Sorong") -> text2.text =
                               "of Guisad/Ferguson/Lourdes Subd., located at Kayang Street." +
                                       " Then Take the Guisad/Ferguson/Lourdes Subd. Jeep"
                           ("Quezon Hill") -> text2.text =
                               "of Quezon Hill, located at #9 Estigoy Building Hiltop Kayang Street. Then Take the Quezon Hill Jeep"
                           ("Baguio-Sablan") -> text2.text =
                               "of Baguio-Sablan, located at KG Baguio, 10 Kayang Street. Then Take the Baguio-Sablan Jeep"
                           ("Baguio-Bayabas") -> text2.text =
                               "of Baguio-Bayabas, located at Upper P. Burgos. Then Take the Baguio-Bayabas Jeep"
                           ("Acupan ") -> text2.text =
                               "of Acupan , located at 66th Infantry Park, F. Calderon Street. Then Take the Acupan Jeep"
                           ("Pinsao & San Carlos") -> text2.text =
                               "of Pinsao & San Carlos, located at Kayang Street. Then Take the Pinsao & San Carlos Jeep"
                           ("San Vicente") -> text2.text =
                               "of San Vicente, located at Balsigan road. Then Take the San Vicente Jeep"
                           ("Samoyao ") -> text2.text =
                               "of Samoyao , located at Lapu Lapu Street. Then Take the Samoyao Jeep"
                           ("Philex") -> text2.text =
                               "of Philex, located at Harrison road. Then Take the Philex Jeep"
                           ("Baguio-Shilan-Acop") -> text2.text =
                               "of Baguio-Shilan-Acop, located at 16 Hilltop Street. Then Take the Baguio-Shilan-Acop Jeep"
                           ("Military Cut Off") -> text2.text =
                               "of Military Cut Off, located at Perfecto Street. Then Take the Military Cut Off Jeep"
                           ("Gabriela Silang ") -> text2.text =
                               "of Gabriela Silang , located at Shanum Street. Then Take the Gabriela Silang  Jeep"
                           ("Campo Sioco") -> text2.text =
                               "of Campo Sioco, located at Hotel Veniz Building, Harrison Rd. Then Take the Campo Sioco Jeep"
                           ("Ambiong ") -> text2.text =
                               "of Ambiong , located at 8 Lapu Lapu Street. Then Take the Ambiong Jeep"
                           ("Palma-Urbano") -> text2.text =
                               "of Palma-Urbano, located at Otek Street. Then Take the Palma-Urbano Jeep"
                           (" Crystal Cave") -> text2.text =
                               "of Crystal Cave, located at Otek Street. Then Take the Crystal Cave Jeep"
                           ("Greenwater") -> text2.text =
                               "of Greenwater, located at Perfecto Street. Then Take the Greenwater Jeep"
                           ("Campo Sioco / BGH") -> text2.text =
                               "of Campo Sioco / BGH, located at Perfecto Street. Then Take the Campo Sioco / BGH Jeep"
                           ("PMA/Kias") -> text2.text =
                               "of PMA/Kias, located at Perfecto Street. Then Take the PMA/Kias Jeep"
                           (" Bakakeng") -> text2.text =
                               "of Bakakeng, located at Perfecto Street. Then Take the Bakakeng Jeep"
                           ("Ucab") -> text2.text =
                               "of Ucab, located at Rajah Soliman Street. Then Take the Ucab Jeep"
                           ("PMA") -> text2.text =
                               "of PMA, located at Shanum Street. Then Take the PMA Jeep"
                           ("Tuba") -> text2.text =
                               "of Tuba, located at Shanum Street. Then Take the Tuba Jeep"
                           ("La Trinidad") -> text2.text =
                               "of La Trinidad, located at Shanum Street. Then Take the La Trinidad Jeep"
                           ("Maria Basa") -> text2.text =
                               "of Maria Basa, located at Jacinto Street. Then Take the Maria Basa Jeep"
                           ("Baguio-Pico") -> text2.text =
                               "of Baguio-Pico, located at 6 Rajah Matanda Street. Then Take the Baguio-Pico Jeep"
                           ("Kitma ") -> text2.text =
                               "of Kitma , located at Harrison road. Then Take the Kitma  Jeep"
                           ("Gabriela Silang") -> text2.text =
                               "of Gabriela Silang, located at 546-526 Gabriela Silang road"
                           ("Magsaysay, Upper") -> text2.text =
                               "of Trancoville Harrison Rd, or walk towards Jacinto Street. "
                           ("Magsaysay") -> text2.text =
                               "of Trancoville Harrison Rd, or walk towards Jacinto Street. "
                           ("New Lucban") -> text2.text =
                               "of Aurora Hill Harrison Rd, or the New Lucban Jeep at Centermall. "
                           ("Manuel A. Roxas") -> text2.text =
                               "of Trancoville Harrison Rd"
                           ("Outlook Drive") -> text2.text =
                               "of Outlook Drive at Jollibee"
                           ("Fairview") -> text2.text =
                               "of Fairview located at Kayang St. behind Abano Square"
                           ("Abanao-Zandueta-Kayong-Chugum-Otek") -> text2.text =
                               "of the Express Naguillian Jeepney infront of Abano Square or walk"
                           ("Cabinet Hill-Teachers Camp") -> text2.text =
                               "of Minesview, or Pacdal located at Jollibee Harrison rd."
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

           //         val origins = location.latitude,location.longitude
//ADD COMPUTE FARE ???

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
                for(place in userMap.places) {
                    var lon2 = place.longitude
                    var lat2 = place.latitude

                    val geocoder: Geocoder
                    val addresses: List<Address>
                    geocoder = Geocoder(applicationContext, Locale.getDefault())
                    //Log.d("Latitude of driver", driverLat.toString())
                    //    Log.d("Longitude read from database", driverLong.toString())

                    if (Lat != null && Long != null) {
                        addresses = geocoder.getFromLocation(Lat, Long, 15)
                        val address: String = addresses[0].getAddressLine(0)



                        val r = 6371 // Radius of the earth


                        val latDistance = Math.toRadians(lat2 - Lat)
                        val lonDistance = Math.toRadians(lon2 - Long)
                        val a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                                + (Math.cos(Math.toRadians(Lat)) * Math.cos(Math.toRadians(lat2))
                                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)))
                        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
                        var distance = r * c * 1000 // convert to meters



                       distance = (distance/1000) //convert to km
                        val distance1 = (Math.round(distance) * 1.40) +10
                        if(distance1 <= 10){
                            dist = 9.0
                        }
                        else if (distance1 > 10 && distance1 < 15.5){
                            dist = 15.0
                        }
                        else if (distance1 > 15.5 && distance1 < 18.5){
                            dist = 25.0
                        }
                        else if (distance1 > 18.5 && distance1 < 20.5){
                            dist = 20.0
                        }
                        else if (distance1 > 20.5 && distance1 < 22.5){
                            dist = 30.0
                        }
                        else if (distance1 > 22.5 && distance1 < 25.5){
                            dist = 25.0
                        }
                        else if (distance1 > 25.5 && distance1 < 30.5){
                            dist = 30.0
                        }
                        else if (distance1 > 35.5 && distance1 < 35.5){
                            dist = 35.0
                        }
                        else {
                            dist = distance
                        }

                        //*************************************************************************
                        val spinner_location: Spinner = findViewById(R.id.spinner)
                        when {
                            address.contains("Otek St") -> spinner_location.setSelection(1)
                            address.contains("Fernando G. Bautista Dr") -> spinner_location.setSelection(
                                1)
                            address.contains("Abanao St") -> spinner_location.setSelection(1)
                            address.contains("Kayang St") -> spinner_location.setSelection(1)
                            address.contains("Shagem St") -> spinner_location.setSelection(1)
                            address.contains("Shanum St") -> spinner_location.setSelection(1)
                            address.contains("Harrison St") -> spinner_location.setSelection(1)
                            address.contains("Zandueta St") -> spinner_location.setSelection(1)
                            address.contains("Shuntug Rd") -> spinner_location.setSelection(1)
                            address.contains("Quirino Hwy") -> spinner_location.setSelection(1)
                            address.contains("Manuel Roxas") -> spinner_location.setSelection(2)
                            address.contains("Private Rd") -> spinner_location.setSelection(2)
                            address.contains("Roxas St") -> spinner_location.setSelection(2)
                            address.contains("Rimando Rd") -> spinner_location.setSelection(2)
                            address.contains("Ambiong-Lubas-Tawang Rd") -> spinner_location.setSelection(
                                3)
                            address.contains("Guitley-Lubas Rd") -> spinner_location.setSelection(3)
                            address.contains("Ambiong-Lamut Road") -> spinner_location.setSelection(
                                3)
                            address.contains("Avelino") -> spinner_location.setSelection(4)
                            address.contains("Freguson Rd") -> spinner_location.setSelection(4)
                            address.contains("Leonor Rivera") -> spinner_location.setSelection(4)
                            address.contains("Kadaclan") -> spinner_location.setSelection(5)
                            address.contains("Johnmary St") -> spinner_location.setSelection(5)
                            address.contains("Shiemadez St") -> spinner_location.setSelection(5)
                            address.contains("Loakan Rd") -> spinner_location.setSelection(5)
                            address.contains("Asin Road") -> spinner_location.setSelection(6)
                            address.contains("Sta. Lucia Road") -> spinner_location.setSelection(6)
                            address.contains("Confier") -> spinner_location.setSelection(6)
                            address.contains("Pine Tree Alley") -> spinner_location.setSelection(6)
                            address.contains("Pinewood St") -> spinner_location.setSelection(6)
                            address.contains("Jasmine Rd") -> spinner_location.setSelection(6)
                            address.contains("Celestial Village") -> spinner_location.setSelection(6)
                            address.contains("Dhalia Rd") -> spinner_location.setSelection(6)
                            address.contains("Atok Trail") -> spinner_location.setSelection(7)
                            address.contains("Sgt. Floresca St") -> spinner_location.setSelection(8)
                            address.contains("Malvar St") -> spinner_location.setSelection(8)
                            address.contains("Lopez Jaena St") -> spinner_location.setSelection(9)
                            address.contains("Gen. De Jesus") -> spinner_location.setSelection(9)
                            address.contains("Rimando Rd") -> spinner_location.setSelection(9)
                            address.contains("Bayan Park Rd") -> spinner_location.setSelection(10)
                            address.contains("Iedesma ext.") -> spinner_location.setSelection(10)
                            address.contains("Rimando Rd") -> spinner_location.setSelection(10)
                            address.contains("Hilltop St") -> spinner_location.setSelection(11)
                            address.contains("Kayang St") -> spinner_location.setSelection(11)
                            address.contains("Magsaysay Ave") -> spinner_location.setSelection(11)
                            address.contains("Ben Palispis Hwy") -> spinner_location.setSelection(12)
                            address.contains("Bengao Rd") -> spinner_location.setSelection(12)
                            address.contains("Santo Nino Road") -> spinner_location.setSelection(12)
                            address.contains("Cuanso Compound Rd") -> spinner_location.setSelection(
                                12)
                            address.contains("Bakakeng Central Rd 1") -> spinner_location.setSelection(
                                12)
                            address.contains("Upper Kitma Rd") -> spinner_location.setSelection(12)
                            address.contains("Lower Kitma Rd") -> spinner_location.setSelection(12)
                            address.contains("De Guia Lane") -> spinner_location.setSelection(12)
                            address.contains("Teacher’s Village Rd") -> spinner_location.setSelection(
                                12)
                            address.contains("Elisabeth Ct") -> spinner_location.setSelection(12)
                            address.contains("Bakakeng Central Rd 3") -> spinner_location.setSelection(
                                12)
                            address.contains("Chapis Village Rd") -> spinner_location.setSelection(
                                12)
                            address.contains("Mission Rd") -> spinner_location.setSelection(12)
                            address.contains("Assumption") -> spinner_location.setSelection(12)
                            address.contains("Pinewood St") -> spinner_location.setSelection(12)
                            address.contains("PNB Village Street") -> spinner_location.setSelection(
                                12)
                            address.contains("Crestwood Court Subdivision") -> spinner_location.setSelection(
                                13)
                            address.contains("Montebello") -> spinner_location.setSelection(13)
                            address.contains("Western Link Circumferential Rd") -> spinner_location.setSelection(
                                13)
                            address.contains("Bakakeng Sur Rd") -> spinner_location.setSelection(13)
                            address.contains("Misereor St") -> spinner_location.setSelection(13)
                            address.contains("Bass Wood Rd") -> spinner_location.setSelection(13)
                            address.contains("Marcoville St") -> spinner_location.setSelection(14)
                            address.contains("Utility Rd") -> spinner_location.setSelection(14)
                            address.contains("Balsigan Rd") -> spinner_location.setSelection(15)
                            address.contains("Bayan Park Rd") -> spinner_location.setSelection(16)
                            address.contains("E Bayan Park Rd") -> spinner_location.setSelection(16)
                            address.contains("Ambiong Rd") -> spinner_location.setSelection(16)
                            address.contains("Bayan Park Rd") -> spinner_location.setSelection(17)
                            address.contains("Bell Church Rd") -> spinner_location.setSelection(18)
                            address.contains("Leonila Hill Rd") -> spinner_location.setSelection(18)
                            address.contains("Evangelista St") -> spinner_location.setSelection(18)
                            address.contains("N Sanitary Camp Rd") -> spinner_location.setSelection(
                                18)
                            address.contains("Hospital Rd") -> spinner_location.setSelection(19)
                            address.contains("Worcester Rd") -> spinner_location.setSelection(19)
                            address.contains("Kennon Rd") -> spinner_location.setSelection(19)
                            address.contains("Lower Brookside") -> spinner_location.setSelection(20)
                            address.contains("Brookside") -> spinner_location.setSelection(20)
                            address.contains("Upper Brookside") -> spinner_location.setSelection(20)
                            address.contains("Rimando Rd") -> spinner_location.setSelection(20)
                            address.contains("Brookspoint Rd") -> spinner_location.setSelection(21)
                            address.contains("Teachers Camp Rd") -> spinner_location.setSelection(22)
                            address.contains("Marcoville St") -> spinner_location.setSelection(22)
                            address.contains("Leonard Wood Rd") -> spinner_location.setSelection(22)
                            address.contains("Camdas Rd") -> spinner_location.setSelection(23)
                            address.contains("Tandang Sora") -> spinner_location.setSelection(23)
                            address.contains("Adarna Rd") -> spinner_location.setSelection(23)
                            address.contains("Gabriela Silang Rd") -> spinner_location.setSelection(
                                23)
                            address.contains("Baguio - La Trinidad - Bontoc Rd") -> spinner_location.setSelection(
                                23)
                            address.contains("Magsaysay Ave") -> spinner_location.setSelection(23)
                            address.contains("Manuel Roxas") -> spinner_location.setSelection(23)
                            address.contains("Kennon Rd") -> spinner_location.setSelection(24)
                            address.contains("Youngland Rd") -> spinner_location.setSelection(24)
                            address.contains("Upper Youngland Rd") -> spinner_location.setSelection(
                                24)
                            address.contains("Middle Youngland Rd") -> spinner_location.setSelection(
                                24)
                            address.contains("Monticello Road") -> spinner_location.setSelection(24)
                            address.contains("Pias") -> spinner_location.setSelection(24)
                            address.contains("Lower Pias St") -> spinner_location.setSelection(24)
                            address.contains("Upper Pias St") -> spinner_location.setSelection(24)
                            address.contains("Giant Sequoia") -> spinner_location.setSelection(24)
                            address.contains("Tames St") -> spinner_location.setSelection(24)
                            address.contains("Douglas Fir") -> spinner_location.setSelection(24)
                            address.contains("Amistad Rd") -> spinner_location.setSelection(24)
                            address.contains("St Jude St") -> spinner_location.setSelection(24)
                            address.contains("Almaciga") -> spinner_location.setSelection(24)
                            address.contains("Binay-an Compound") -> spinner_location.setSelection(
                                24)
                            address.contains("Camp 7 W Rd") -> spinner_location.setSelection(24)
                            address.contains("Parisas St") -> spinner_location.setSelection(24)
                            address.contains("Amparo Heights St") -> spinner_location.setSelection(
                                24)
                            address.contains("Cosmos Street") -> spinner_location.setSelection(24)
                            address.contains("Daisy St") -> spinner_location.setSelection(24)
                            address.contains("Milflores") -> spinner_location.setSelection(24)
                            address.contains("Bernard") -> spinner_location.setSelection(24)
                            address.contains("Bernadita") -> spinner_location.setSelection(24)
                            address.contains("Encarnacion") -> spinner_location.setSelection(24)
                            address.contains("Anges") -> spinner_location.setSelection(24)
                            address.contains("Lexber Drive 1") -> spinner_location.setSelection(24)
                            address.contains("Lexber Drive 2") -> spinner_location.setSelection(24)
                            address.contains("Western Link Circumferential Rd ") -> spinner_location.setSelection(
                                24)
                            address.contains("St John St") -> spinner_location.setSelection(24)
                            address.contains("St Michael St") -> spinner_location.setSelection(24)
                            address.contains("St Luke St") -> spinner_location.setSelection(24)
                            address.contains("St Francis St") -> spinner_location.setSelection(24)
                            address.contains("St Benedict St") -> spinner_location.setSelection(24)
                            address.contains("St Philipps St") -> spinner_location.setSelection(24)
                            address.contains("St Daniel St") -> spinner_location.setSelection(24)
                            address.contains("St Joseph St") -> spinner_location.setSelection(24)
                            address.contains("St Peter St") -> spinner_location.setSelection(24)
                            address.contains("St Simon St") -> spinner_location.setSelection(24)
                            address.contains("St Mark St") -> spinner_location.setSelection(24)
                            address.contains("St Ruth St") -> spinner_location.setSelection(24)
                            address.contains("St Martha St") -> spinner_location.setSelection(24)
                            address.contains("Tony Agpaoa Dr") -> spinner_location.setSelection(24)
                            address.contains("Agpaoa Street Camp 7") -> spinner_location.setSelection(
                                24)
                            address.contains("Alexandria Dr") -> spinner_location.setSelection(24)
                            address.contains("Ebony") -> spinner_location.setSelection(24)
                            address.contains("Spruce") -> spinner_location.setSelection(24)
                            address.contains("Red Mangrove") -> spinner_location.setSelection(24)
                            address.contains("White Milk Wood") -> spinner_location.setSelection(24)
                            address.contains("Apple Leaf") -> spinner_location.setSelection(24)
                            address.contains("Chestnut Oak Tree") -> spinner_location.setSelection(
                                24)
                            address.contains("White Cedar") -> spinner_location.setSelection(24)
                            address.contains("Camp 8 Rd") -> spinner_location.setSelection(25)
                            address.contains("Camp Allen Rd") -> spinner_location.setSelection(26)
                            address.contains("P Zamora") -> spinner_location.setSelection(26)
                            address.contains("Sepic Rd") -> spinner_location.setSelection(27)
                            address.contains("Ferguson Rd") -> spinner_location.setSelection(27)
                            address.contains("Roman Ayson Rd") -> spinner_location.setSelection(27)
                            address.contains("Brower Rd") -> spinner_location.setSelection(27)
                            address.contains("Upper Fairview Ferguson Rd") -> spinner_location.setSelection(
                                27)
                            address.contains("Sunflower") -> spinner_location.setSelection(27)
                            address.contains("City Camp Rd") -> spinner_location.setSelection(28)
                            address.contains("City Camp Rd") -> spinner_location.setSelection(29)
                            address.contains("Jose Felipe St") -> spinner_location.setSelection(29)
                            address.contains("City Camp Alley") -> spinner_location.setSelection(29)
                            address.contains("Country Club Rd") -> spinner_location.setSelection(30)
                            address.contains("Artiaga") -> spinner_location.setSelection(30)
                            address.contains("Outlook Rd S") -> spinner_location.setSelection(30)
                            address.contains("Pedro Paterno") -> spinner_location.setSelection(30)
                            address.contains("Bado Dangwa") -> spinner_location.setSelection(31)
                            address.contains("Ferguson Rd") -> spinner_location.setSelection(31)
                            address.contains("Pucay Subdivision Rd") -> spinner_location.setSelection(
                                31)
                            address.contains("Easter Rd") -> spinner_location.setSelection(31)
                            address.contains("Hillside Rd") -> spinner_location.setSelection(32)
                            address.contains("Gabriela Silang Rd") -> spinner_location.setSelection(
                                32)
                            address.contains("Hillside Rd") -> spinner_location.setSelection(33)
                            address.contains("Loro Street") -> spinner_location.setSelection(34)
                            address.contains("Adarna Street") -> spinner_location.setSelection(34)
                            address.contains("Manzanillo") -> spinner_location.setSelection(34)
                            address.contains("Maya") -> spinner_location.setSelection(34)
                            address.contains("Aguila Street") -> spinner_location.setSelection(34)
                            address.contains("Kanaryu") -> spinner_location.setSelection(34)
                            address.contains("Rd1") -> spinner_location.setSelection(34)
                            address.contains("Rd2") -> spinner_location.setSelection(34)
                            address.contains("Rd3") -> spinner_location.setSelection(34)
                            address.contains("Saint Paul") -> spinner_location.setSelection(35)
                            address.contains("Saint John") -> spinner_location.setSelection(35)
                            address.contains("Saint Jude") -> spinner_location.setSelection(35)
                            address.contains("Dominican Hill Rd") -> spinner_location.setSelection(
                                35)
                            address.contains("Extension Rd") -> spinner_location.setSelection(35)
                            address.contains("St Anthony") -> spinner_location.setSelection(35)
                            address.contains("St Theresa Ext.") -> spinner_location.setSelection(35)
                            address.contains("Santo Tomas Rd") -> spinner_location.setSelection(36)
                            address.contains("Stone Hill Rd") -> spinner_location.setSelection(36)
                            address.contains("Foggy Hills") -> spinner_location.setSelection(36)
                            address.contains("Tello") -> spinner_location.setSelection(36)
                            address.contains("Adiwang Road") -> spinner_location.setSelection(36)
                            address.contains("Ridge View") -> spinner_location.setSelection(36)
                            address.contains("Balballo-Licano") -> spinner_location.setSelection(36)
                            address.contains("Bradeya") -> spinner_location.setSelection(36)
                            address.contains("Aurello St") -> spinner_location.setSelection(36)
                            address.contains("Pangon") -> spinner_location.setSelection(36)
                            address.contains("Sampaguita St") -> spinner_location.setSelection(36)
                            address.contains("Rose St") -> spinner_location.setSelection(36)
                            address.contains("Control Point") -> spinner_location.setSelection(36)
                            address.contains("Sylvia St") -> spinner_location.setSelection(36)
                            address.contains("Dhalia St") -> spinner_location.setSelection(36)
                            address.contains("DPS Compound Access Road") -> spinner_location.setSelection(
                                37)
                            address.contains("R. Villalon St") -> spinner_location.setSelection(38)
                            address.contains("Utility Rd") -> spinner_location.setSelection(38)
                            address.contains("V. Martinez St") -> spinner_location.setSelection(38)
                            address.contains("North Dr") -> spinner_location.setSelection(38)
                            address.contains("Upper Session Rd") -> spinner_location.setSelection(38)
                            address.contains("Camia Street") -> spinner_location.setSelection(39)
                            address.contains("Cerise Lane") -> spinner_location.setSelection(39)
                            address.contains("Fairview Rd") -> spinner_location.setSelection(39)
                            address.contains("Upper Fairview Ferguson Road") -> spinner_location.setSelection(
                                39)
                            address.contains("Cherry Blossoms") -> spinner_location.setSelection(39)
                            address.contains("Magnolia Alley") -> spinner_location.setSelection(39)
                            address.contains("Dahila Road") -> spinner_location.setSelection(39)
                            address.contains("Santo Rosario Alley") -> spinner_location.setSelection(
                                40)
                            address.contains("Happy Homes Subd.Rd") -> spinner_location.setSelection(
                                40)
                            address.contains("Legarda Rd") -> spinner_location.setSelection(40)
                            address.contains("Dr Carino St") -> spinner_location.setSelection(40)
                            address.contains("Europa Entrance Road") -> spinner_location.setSelection(
                                40)
                            address.contains("Agoo - Baguio Road") -> spinner_location.setSelection(
                                40)
                            address.contains("Demonstration Mines Road") -> spinner_location.setSelection(
                                41)
                            address.contains("Quezon Ave") -> spinner_location.setSelection(41)
                            address.contains("Gabriela Silang Rd") -> spinner_location.setSelection(
                                42)
                            address.contains("Dr Carino St") -> spinner_location.setSelection(43)
                            address.contains("Doctor Carino Ext.") -> spinner_location.setSelection(
                                43)
                            address.contains("Bright Red St") -> spinner_location.setSelection(43)
                            address.contains("Everlasting St") -> spinner_location.setSelection(43)
                            address.contains("Yangco St") -> spinner_location.setSelection(44)
                            address.contains("Brent Rd") -> spinner_location.setSelection(44)
                            address.contains("Gen. Luna Rd") -> spinner_location.setSelection(44)
                            address.contains("Laubach Rd") -> spinner_location.setSelection(44)
                            address.contains("S Laurel") -> spinner_location.setSelection(44)
                            address.contains("Lower Laurel") -> spinner_location.setSelection(44)
                            address.contains("Lopez St ") -> spinner_location.setSelection(44)
                            address.contains("Sumulong St") -> spinner_location.setSelection(44)
                            address.contains("Upper Bonifacio St") -> spinner_location.setSelection(
                                44)
                            address.contains("Assumption Rd") -> spinner_location.setSelection(45)
                            address.contains("Upper Bonifacio St") -> spinner_location.setSelection(
                                45)
                            address.contains("Gibraltar Rd") -> spinner_location.setSelection(46)
                            address.contains("Golden Box") -> spinner_location.setSelection(46)
                            address.contains("Rose") -> spinner_location.setSelection(46)
                            address.contains("Gibraltar Extension") -> spinner_location.setSelection(
                                46)
                            address.contains("Green Water Vill.Rd") -> spinner_location.setSelection(
                                47)
                            address.contains("Pucay Subdivision Rd") -> spinner_location.setSelection(
                                48)
                            address.contains("Ferguson Rd") -> spinner_location.setSelection(48)
                            address.contains("Lt Tacay Rd") -> spinner_location.setSelection(48)
                            address.contains("Green Ln") -> spinner_location.setSelection(49)
                            address.contains("Ivory Ln") -> spinner_location.setSelection(49)
                            address.contains("Avelino") -> spinner_location.setSelection(49)
                            address.contains("Tangerine Lane") -> spinner_location.setSelection(49)
                            address.contains("Badihoy") -> spinner_location.setSelection(49)
                            address.contains("VOA Road") -> spinner_location.setSelection(50)
                            address.contains("Happy Hollow") -> spinner_location.setSelection(50)
                            address.contains("Ordonio Dr") -> spinner_location.setSelection(50)
                            address.contains("Sheridan Dr") -> spinner_location.setSelection(50)
                            address.contains("19th Tee Path") -> spinner_location.setSelection(50)
                            address.contains("Brgy.Happy Homes") -> spinner_location.setSelection(51)
                            address.contains("Harrison Rd") -> spinner_location.setSelection(52)
                            address.contains("Carantes St") -> spinner_location.setSelection(52)
                            address.contains("T.Claudia St") -> spinner_location.setSelection(52)
                            address.contains("Lower Mabini St") -> spinner_location.setSelection(52)
                            address.contains("Hillside Rd") -> spinner_location.setSelection(53)
                            address.contains("Holy Ghost Hill Ext. Rd") -> spinner_location.setSelection(
                                54)
                            address.contains("Holy Ghost Road Extension") -> spinner_location.setSelection(
                                54)
                            address.contains("Sumulong Extension") -> spinner_location.setSelection(
                                55)
                            address.contains("Honeymoon Rd") -> spinner_location.setSelection(56)
                            address.contains("Bakakeng North Rd") -> spinner_location.setSelection(
                                57)
                            address.contains("Justice Village Rd") -> spinner_location.setSelection(
                                57)
                            address.contains("Lower Justice Village Rd") -> spinner_location.setSelection(
                                57)
                            address.contains("N Santo Tomas Rd") -> spinner_location.setSelection(57)
                            address.contains("Holy Ghost Hill Ext. Rd") -> spinner_location.setSelection(
                                58)
                            address.contains("Yangco Rd") -> spinner_location.setSelection(58)
                            address.contains("Naguilian Rd") -> spinner_location.setSelection(59)
                            address.contains("PSHS Rd") -> spinner_location.setSelection(59)
                            address.contains("Add Rd") -> spinner_location.setSelection(59)
                            address.contains("Idogan Village Rd") -> spinner_location.setSelection(
                                59)
                            address.contains("St Anthony Rd") -> spinner_location.setSelection(59)
                            address.contains("St Patrick Rd") -> spinner_location.setSelection(59)
                            address.contains("San Carlos Heights") -> spinner_location.setSelection(
                                59)
                            address.contains("Lamug") -> spinner_location.setSelection(59)
                            address.contains("Ramon Mitra") -> spinner_location.setSelection(59)
                            address.contains("Ruby") -> spinner_location.setSelection(59)
                            address.contains("Jade") -> spinner_location.setSelection(59)
                            address.contains("Pearl") -> spinner_location.setSelection(59)
                            address.contains("Diamond") -> spinner_location.setSelection(59)
                            address.contains("Sapphire") -> spinner_location.setSelection(59)
                            address.contains("Aquamarine") -> spinner_location.setSelection(59)
                            address.contains("Onyx") -> spinner_location.setSelection(59)
                            address.contains("Amethyst") -> spinner_location.setSelection(59)
                            address.contains("Balenben Rd") -> spinner_location.setSelection(59)
                            address.contains("Luna St") -> spinner_location.setSelection(59)
                            address.contains("Mangol") -> spinner_location.setSelection(59)
                            address.contains("Tacio") -> spinner_location.setSelection(59)
                            address.contains("Mangos Road") -> spinner_location.setSelection(59)
                            address.contains("Conon") -> spinner_location.setSelection(59)
                            address.contains("La Chesa Road") -> spinner_location.setSelection(59)
                            address.contains("Assumption Rd") -> spinner_location.setSelection(60)
                            address.contains("Fr. Carlu St") -> spinner_location.setSelection(60)
                            address.contains("Gen. Luna Rd") -> spinner_location.setSelection(60)
                            address.contains("Mabini St") -> spinner_location.setSelection(60)
                            address.contains("Anacleto St") -> spinner_location.setSelection(61)
                            address.contains("Kalantiao Street") -> spinner_location.setSelection(61)
                            address.contains("Magsaysay Ave") -> spinner_location.setSelection(61)
                            address.contains("Dagohoy") -> spinner_location.setSelection(61)
                            address.contains("Naguilian Rd") -> spinner_location.setSelection(62)
                            address.contains("Labsan St") -> spinner_location.setSelection(62)
                            address.contains("Hilltop St") -> spinner_location.setSelection(63)
                            address.contains("Zandueta St") -> spinner_location.setSelection(63)
                            address.contains("Loakan Rd") -> spinner_location.setSelection(64)
                            address.contains("Gen. Lim St") -> spinner_location.setSelection(66)
                            address.contains("Kisad Rd") -> spinner_location.setSelection(66)
                            address.contains("Legarda Rd") -> spinner_location.setSelection(66)
                            address.contains("Bukaneg St") -> spinner_location.setSelection(66)
                            address.contains("M.H. Del Pilar St") -> spinner_location.setSelection(
                                66)
                            address.contains("Shanum St") -> spinner_location.setSelection(66)
                            address.contains("Perfecto St") -> spinner_location.setSelection(66)
                            address.contains("Lake Dr") -> spinner_location.setSelection(66)
                            address.contains("Jose Abad Santos Dr") -> spinner_location.setSelection(
                                66)
                            address.contains("Harrison Rd") -> spinner_location.setSelection(66)
                            address.contains("Montinola") -> spinner_location.setSelection(66)
                            address.contains("Loakan Rd") -> spinner_location.setSelection(67)
                            address.contains("Alphaville St") -> spinner_location.setSelection(67)
                            address.contains("Youngland Rd") -> spinner_location.setSelection(67)
                            address.contains("Runway Sideroad") -> spinner_location.setSelection(67)
                            address.contains("Cliff Drive") -> spinner_location.setSelection(67)
                            address.contains("Manga Rd") -> spinner_location.setSelection(67)
                            address.contains("Demonstration Mines Road") -> spinner_location.setSelection(
                                67)
                            address.contains("Loakan Rd") -> spinner_location.setSelection(67)
                            address.contains("Cudirao Rd") -> spinner_location.setSelection(67)
                            address.contains("Fatima St") -> spinner_location.setSelection(68)
                            address.contains("Rimando Rd") -> spinner_location.setSelection(68)
                            address.contains("Gen. De Jesus") -> spinner_location.setSelection(68)
                            address.contains("Bayan Park Rd") -> spinner_location.setSelection(68)
                            address.contains("Lopez Jaena St") -> spinner_location.setSelection(68)
                            address.contains("Hamada Subdivision Rd") -> spinner_location.setSelection(
                                69)
                            address.contains("Queen of Peace Rd") -> spinner_location.setSelection(
                                69)
                            address.contains("Mystical St") -> spinner_location.setSelection(70)
                            address.contains("Queen of Peace Rd") -> spinner_location.setSelection(
                                70)
                            address.contains("Queen of Angels Rd") -> spinner_location.setSelection(
                                71)
                            address.contains("Lourdes Subd. Rd") -> spinner_location.setSelection(71)
                            address.contains("Dominican Hill Rd") -> spinner_location.setSelection(
                                71)
                            address.contains("Mystical St") -> spinner_location.setSelection(71)
                            address.contains("Lower Rock Quarry") -> spinner_location.setSelection(
                                71)
                            address.contains("Ignacio Villamor St") -> spinner_location.setSelection(
                                72)
                            address.contains("Park Road") -> spinner_location.setSelection(72)
                            address.contains("J.Felipe St") -> spinner_location.setSelection(72)
                            address.contains("Baguio - Bua - Itogon Rd") -> spinner_location.setSelection(
                                72)
                            address.contains("Gibraltar Rd") -> spinner_location.setSelection(72)
                            address.contains("Wright Park Horse Stables") -> spinner_location.setSelection(
                                72)
                            address.contains("V. De Los Reyes St.") -> spinner_location.setSelection(
                                73)
                            address.contains("Private Rd") -> spinner_location.setSelection(74)
                            address.contains("Magsaysay Ave") -> spinner_location.setSelection(75)
                            address.contains("Private Rd") -> spinner_location.setSelection(75)
                            address.contains("Magsaysay Ave") -> spinner_location.setSelection(76)
                            address.contains("Upper P. Burgos") -> spinner_location.setSelection(76)
                            address.contains("Hilltop St") -> spinner_location.setSelection(76)
                            address.contains("Gen. Luna Rd") -> spinner_location.setSelection(77)
                            address.contains("E Jacinto St") -> spinner_location.setSelection(77)
                            address.contains("Perfecto St") -> spinner_location.setSelection(77)
                            address.contains("Magsaysay Ave") -> spinner_location.setSelection(77)
                            address.contains("Brent Rd") -> spinner_location.setSelection(78)
                            address.contains("Leonard Wood Rd") -> spinner_location.setSelection(78)
                            address.contains("Lower Brookside") -> spinner_location.setSelection(78)
                            address.contains("P Zamora") -> spinner_location.setSelection(79)
                            address.contains("Chrome St") -> spinner_location.setSelection(80)
                            address.contains("Bronze Road") -> spinner_location.setSelection(80)
                            address.contains("Ortega St") -> spinner_location.setSelection(80)
                            address.contains("Ramon Mitra") -> spinner_location.setSelection(80)
                            address.contains("Paraan St") -> spinner_location.setSelection(80)
                            address.contains("Mallare") -> spinner_location.setSelection(80)
                            address.contains("Lt Tacay Rd") -> spinner_location.setSelection(80)
                            address.contains("Tin") -> spinner_location.setSelection(80)
                            address.contains("Gold") -> spinner_location.setSelection(80)
                            address.contains("Quezon Hill Rd 2") -> spinner_location.setSelection(80)
                            address.contains("Abellera St.") -> spinner_location.setSelection(80)
                            address.contains("Zarate") -> spinner_location.setSelection(80)
                            address.contains("Arvisu") -> spinner_location.setSelection(80)
                            address.contains("T. M. Kalaw St") -> spinner_location.setSelection(81)
                            address.contains("Governor Center Extn") -> spinner_location.setSelection(
                                81)
                            address.contains("Upper Session Rd") -> spinner_location.setSelection(81)
                            address.contains("Governor Center Rd") -> spinner_location.setSelection(
                                81)
                            address.contains("Harrison Rd") -> spinner_location.setSelection(81)
                            address.contains("Gibraltar Rd") -> spinner_location.setSelection(82)
                            address.contains("Modesta Street") -> spinner_location.setSelection(82)
                            address.contains("P. Ledesma St") -> spinner_location.setSelection(83)
                            address.contains("Lower Brookside") -> spinner_location.setSelection(83)
                            address.contains("T. Bugallon St") -> spinner_location.setSelection(83)
                            address.contains("Leesma St") -> spinner_location.setSelection(83)
                            address.contains("Lanzones") -> spinner_location.setSelection(83)
                            address.contains("Rimando Rd") -> spinner_location.setSelection(84)
                            address.contains("Guevarra St") -> spinner_location.setSelection(84)
                            address.contains("T. Bugallon St") -> spinner_location.setSelection(84)
                            address.contains("Queen of Peace Rd") -> spinner_location.setSelection(
                                85)
                            address.contains("Hamada Subdivision Rd") -> spinner_location.setSelection(
                                85)
                            address.contains("New Lucban Rd") -> spinner_location.setSelection(86)
                            address.contains("Private Rd") -> spinner_location.setSelection(86)
                            address.contains("New Lucban Ext") -> spinner_location.setSelection(86)
                            address.contains("Pines City Residences") -> spinner_location.setSelection(
                                86)
                            address.contains("V. De Los Reyes St.") -> spinner_location.setSelection(
                                87)
                            address.contains("Outlook Dr S") -> spinner_location.setSelection(87)
                            address.contains("Regidor St") -> spinner_location.setSelection(88)
                            address.contains("Maria Basa St") -> spinner_location.setSelection(88)
                            address.contains("Amsing Road") -> spinner_location.setSelection(88)
                            address.contains("Brookspoint Rd") -> spinner_location.setSelection(88)
                            address.contains("Pacdal Liteng") -> spinner_location.setSelection(88)
                            address.contains("Litteng Road") -> spinner_location.setSelection(88)
                            address.contains("Siapno Rd") -> spinner_location.setSelection(88)
                            address.contains("M. Gomez") -> spinner_location.setSelection(89)
                            address.contains("Upper P. Burgos") -> spinner_location.setSelection(89)
                            address.contains("Lower P. Burgos") -> spinner_location.setSelection(89)
                            address.contains("Magsaysay Ave") -> spinner_location.setSelection(89)
                            address.contains("P Zamora") -> spinner_location.setSelection(90)
                            address.contains("Lower P. Burgos") -> spinner_location.setSelection(90)
                            address.contains("Palma St") -> spinner_location.setSelection(91)
                            address.contains("Queen of Peace Rd") -> spinner_location.setSelection(
                                91)
                            address.contains("Urbano St") -> spinner_location.setSelection(91)
                            address.contains("Legarda Rd") -> spinner_location.setSelection(91)
                            address.contains("Labsan St") -> spinner_location.setSelection(91)
                            address.contains("Pinpin St") -> spinner_location.setSelection(91)
                            address.contains("City Camp Rd") -> spinner_location.setSelection(91)
                            address.contains("Worcester Rd") -> spinner_location.setSelection(92)
                            address.contains("Pinget") -> spinner_location.setSelection(93)
                            address.contains("Rainbow Drive") -> spinner_location.setSelection(93)
                            address.contains("Sunrise Road") -> spinner_location.setSelection(93)
                            address.contains("Camdas Rd") -> spinner_location.setSelection(93)
                            address.contains("Lower Pinget Road 2") -> spinner_location.setSelection(
                                93)
                            address.contains("Lower Pinget Purok 3") -> spinner_location.setSelection(
                                93)
                            address.contains("Pinsao Rd") -> spinner_location.setSelection(93)
                            address.contains("Upper Pinget") -> spinner_location.setSelection(93)
                            address.contains("Pinsao Rd") -> spinner_location.setSelection(94)
                            address.contains("Maligcong Village Road") -> spinner_location.setSelection(
                                94)
                            address.contains("Lt Tacay Rd") -> spinner_location.setSelection(94)
                            address.contains("Benin Rd") -> spinner_location.setSelection(95)
                            address.contains("Lt Tacay Rd") -> spinner_location.setSelection(95)
                            address.contains("Long Long Feder Rd") -> spinner_location.setSelection(
                                95)
                            address.contains("Lakshmi Drive") -> spinner_location.setSelection(95)
                            address.contains("Maria Pucay Rd") -> spinner_location.setSelection(95)
                            address.contains("Camp 8 Rd") -> spinner_location.setSelection(96)
                            address.contains("Puliwes Rd") -> spinner_location.setSelection(96)
                            address.contains("Outlook Dr S") -> spinner_location.setSelection(97)
                            address.contains("C. Arellano St") -> spinner_location.setSelection(97)
                            address.contains("C. Apostol St") -> spinner_location.setSelection(97)
                            address.contains("Moran St") -> spinner_location.setSelection(97)
                            address.contains("Torres St") -> spinner_location.setSelection(97)
                            address.contains("F. Castro") -> spinner_location.setSelection(97)
                            address.contains("J.Felipe St") -> spinner_location.setSelection(97)
                            address.contains("Baltazar St") -> spinner_location.setSelection(97)
                            address.contains("Gibraltar Rd") -> spinner_location.setSelection(97)
                            address.contains("19 Ponce") -> spinner_location.setSelection(98)
                            address.contains("Quezon Hill Rd 1") -> spinner_location.setSelection(98)
                            address.contains("Mallare") -> spinner_location.setSelection(98)
                            address.contains("Quezon Hill Rd") -> spinner_location.setSelection(98)
                            address.contains("Lt. J. Artiaga") -> spinner_location.setSelection(98)
                            address.contains("Escoda") -> spinner_location.setSelection(98)
                            address.contains("Quezon Hill Rd 2") -> spinner_location.setSelection(98)
                            address.contains("Golden Shower St") -> spinner_location.setSelection(98)
                            address.contains("Lt Tacay Rd") -> spinner_location.setSelection(99)
                            address.contains("Chrome St") -> spinner_location.setSelection(99)
                            address.contains("Mercury") -> spinner_location.setSelection(99)
                            address.contains("Bronze Road") -> spinner_location.setSelection(99)
                            address.contains("Silver") -> spinner_location.setSelection(99)
                            address.contains("Copper") -> spinner_location.setSelection(99)
                            address.contains("Copper Ext") -> spinner_location.setSelection(99)
                            address.contains("Iron") -> spinner_location.setSelection(99)
                            address.contains("Quirino Hill Rd") -> spinner_location.setSelection(100)
                            address.contains("Bell Church Rd") -> spinner_location.setSelection(100)
                            address.contains("Heavenly") -> spinner_location.setSelection(100)
                            address.contains(" Lower Quirino Hill Rd") -> spinner_location.setSelection(
                                101)
                            address.contains("Quirino Hill Rd") -> spinner_location.setSelection(102)
                            address.contains("Lodge") -> spinner_location.setSelection(102)
                            address.contains("Heavenly") -> spinner_location.setSelection(102)
                            address.contains("Lodge") -> spinner_location.setSelection(103)
                            address.contains("Quirino Hill Rd") -> spinner_location.setSelection(103)
                            address.contains("Camdas Rd") -> spinner_location.setSelection(103)
                            address.contains("Dahlia St") -> spinner_location.setSelection(104)
                            address.contains("Camella St") -> spinner_location.setSelection(104)
                            address.contains("Everlasting St") -> spinner_location.setSelection(104)
                            address.contains("Magnolia St") -> spinner_location.setSelection(104)
                            address.contains("Dr Carino St") -> spinner_location.setSelection(104)
                            address.contains("Jasmin St") -> spinner_location.setSelection(104)
                            address.contains("Gladiola") -> spinner_location.setSelection(104)
                            address.contains("Camelia St") -> spinner_location.setSelection(104)
                            address.contains("Upper Quarry") -> spinner_location.setSelection(104)

//UPDATE

                        }
                        //      textViewUserLoc.text="Latitude:"+driverLat.toString()+", Longitude: "+driverLong.toString()
                        println("got this far 8")

                        val driverLoc = LatLng(Lat, Long)
                        val markerOptions =
                            MarkerOptions().position(driverLoc).title("Your Location")
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
               userMap.title.equals("Lemon and Olives") -> spinner_destination.setSelection(88)
               userMap.title.equals("Grumpy Joe") -> spinner_destination.setSelection(49)
               userMap.title.equals("Sajj") -> spinner_destination.setSelection(79)
               userMap.title.equals("Abonabil Diner") -> spinner_destination.setSelection(87)
               userMap.title.equals("Pizza Hut") -> spinner_destination.setSelection(41)
               userMap.title.equals("Mcdonald's") -> spinner_destination.setSelection(1)
               userMap.title.equals("Greenwich") -> spinner_destination.setSelection(123)
               userMap.title.equals("University of the Cordilleras") -> spinner_destination.setSelection(132)
               userMap.title.equals("University of Baguio") -> spinner_destination.setSelection(40)
               userMap.title.equals("Pines City Colleges") -> spinner_destination.setSelection(79)
//***************************************************************************************************************
               userMap.title.equals("AMA Computer University") -> spinner_destination.setSelection(
                   75)
               userMap.title.equals("ST. Louis University") -> spinner_destination.setSelection(13)
               userMap.title.equals("University of the Philippines Baguio") -> spinner_destination.setSelection(
                   132)
               userMap.title.equals("STI College") -> spinner_destination.setSelection(86)
               userMap.title.equals("Philippine Women's University - CDCEC Baguio ") -> spinner_destination.setSelection(
                   60)
               userMap.title.equals("Informatics Institute Baguio") -> spinner_destination.setSelection(
                   126)
               userMap.title.equals("National Baguio University") -> spinner_destination.setSelection(
                   34)
               userMap.title.equals("Philippine Military Academy") -> spinner_destination.setSelection(
                   67)
               userMap.title.equals("Remnant International College") -> spinner_destination.setSelection(
                   24)
               userMap.title.equals("International Christian College") -> spinner_destination.setSelection(
                   59)
               userMap.title.equals("Keystone College") -> spinner_destination.setSelection(19)
               userMap.title.equals("The Mansion") -> spinner_destination.setSelection(72)
               userMap.title.equals("Yellow Trail") -> spinner_destination.setSelection(50)
               userMap.title.equals("Bell House") -> spinner_destination.setSelection(50)
               userMap.title.equals("Cemetery of Negativism") -> spinner_destination.setSelection(50)
               userMap.title.equals("Bencab Museum") -> spinner_destination.setSelection(6)
               userMap.title.equals("Burnham Park") -> spinner_destination.setSelection(65)
               userMap.title.equals("Baguio Museum") -> spinner_destination.setSelection(81)
               userMap.title.equals("MT.Yangbew") -> spinner_destination.setSelection(131)
               userMap.title.equals("Botanical Garden") -> spinner_destination.setSelection(22)
               userMap.title.equals("Mines View Park") -> spinner_destination.setSelection(83)
               userMap.title.equals("Camp John Hay") -> spinner_destination.setSelection(67)
               userMap.title.equals("Strawberry Farm") -> spinner_destination.setSelection(23)
               userMap.title.equals("Bell Church") -> spinner_destination.setSelection(23)
               userMap.title.equals("Tree Top Adventure") -> spinner_destination.setSelection(67)
               userMap.title.equals("Tam-awan village") -> spinner_destination.setSelection(95)
               userMap.title.equals("Wright Park") -> spinner_destination.setSelection(67)
               userMap.title.equals("Govenor Pack Road") -> spinner_destination.setSelection(132)
               userMap.title.equals("Magsaysay Avenue") -> spinner_destination.setSelection(77)
               userMap.title.equals("Harrison Road") -> spinner_destination.setSelection(52)
               userMap.title.equals("Veterans Loop") -> spinner_destination.setSelection(65)
               userMap.title.equals("SkyWorld") -> spinner_destination.setSelection(122)
               userMap.title.equals("KFC Lower Session") -> spinner_destination.setSelection(52)
               userMap.title.equals("Victory Liner") -> spinner_destination.setSelection(14)
               userMap.title.equals("Pinesview Bridge") -> spinner_destination.setSelection(125)
               userMap.title.equals("Centermall") -> spinner_destination.setSelection(61)
               userMap.title.equals("Jacinto Street") -> spinner_destination.setSelection(77)
               userMap.title.equals("Kayang Street") -> spinner_destination.setSelection(1)
               userMap.title.equals("Baguio Night Market") -> spinner_destination.setSelection(65)
               userMap.title.equals("Mt.Kalugong") -> spinner_destination.setSelection(129)
               userMap.title.equals("Baguio Central University") -> spinner_destination.setSelection(
                   0)

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
    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }



}






// [END maps_marker_on_map_ready]