package com.example.googlemapkotlin

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
//extend OnMapReadyCallback for maping
class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap

    private lateinit var lastLocation: Location

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }
    override fun onMapReady(p0: GoogleMap) {
        mMap=p0

        //Adding markers to map

        val latLng=LatLng(28.6139,77.2090)
        val markerOptions:MarkerOptions=MarkerOptions().position(latLng).title("New Delhi")

        // moving camera and zoom map

        val zoomLevel = 12.0f //This goes up to 21


        mMap.let {
            it!!.addMarker(markerOptions)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        }
    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        setUpMap()
//        mMap = googleMap
//        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
//        mMap.uiSettings.isZoomControlsEnabled = true
//        mMap.setOnMarkerClickListener(this)
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        mMap.isMyLocationEnabled = true
//        // Construct a FusedLocationProviderClient.
//        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
//            // Got last known location. In some rare situations this can be null.
//            if (location != null) {
//                lastLocation = location
//                val currentLatLng = LatLng(location.latitude, location.longitude)
//                //icon convert into bitmap
//                var icon = BitmapDescriptorFactory.fromResource(R.drawable.logo)
//                //add custom marker on map
//                mMap.addMarker(MarkerOptions().position(currentLatLng).icon(icon))
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
//            }
//        }
//    }

    //for cheking peermssoin
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
    }

    override fun onMarkerClick(p0: Marker?) = false
}


