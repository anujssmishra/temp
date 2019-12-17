package com.example.temp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_maps.btn1
import kotlinx.android.synthetic.main.activity_maps.btn2
import kotlinx.android.synthetic.main.activity_maps.btn3
import kotlinx.android.synthetic.main.activity_registration.*

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        findViewById<Button>(R.id.btn1).setBackgroundResource(R.drawable.top_bar)
        findViewById<Button>(R.id.btn1).setTextColor(Color.parseColor("#FFFFFF"))
        val b1 = findViewById<Button>(R.id.btn1)
        val b2 = findViewById<Button>(R.id.btn2)
        val b3 = findViewById<Button>(R.id.btn3)

        btn1.setOnClickListener {
            b1.setBackgroundResource(R.drawable.top_bar)
            b2.setBackgroundResource(R.drawable.edit_text_background)
            b3.setBackgroundResource(R.drawable.edit_text_background)
            b1.setTextColor(Color.parseColor("#FFFFFF"))
            b2.setTextColor(Color.parseColor("#80FFFFFF"))
            b3.setTextColor(Color.parseColor("#80FFFFFF"))
        }
        btn2.setOnClickListener {
            b1.setBackgroundResource(R.drawable.edit_text_background)
            b2.setBackgroundResource(R.drawable.gender_on_click)
            b3.setBackgroundResource(R.drawable.edit_text_background)
            b2.setTextColor(Color.parseColor("#FFFFFF"))
            b1.setTextColor(Color.parseColor("#80FFFFFF"))
            b3.setTextColor(Color.parseColor("#80FFFFFF"))
        }
        btn3.setOnClickListener {
            b1.setBackgroundResource(R.drawable.edit_text_background)
            b2.setBackgroundResource(R.drawable.edit_text_background)
            b3.setBackgroundResource(R.drawable.top_bar2)
            b3.setTextColor(Color.parseColor("#FFFFFF"))
            b1.setTextColor(Color.parseColor("#80FFFFFF"))
            b2.setTextColor(Color.parseColor("#80FFFFFF"))
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}