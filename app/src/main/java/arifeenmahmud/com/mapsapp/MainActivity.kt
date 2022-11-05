package arifeenmahmud.com.mapsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(),OnMapReadyCallback
    {

        lateinit var myMap:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

        override fun onMapReady(gMap: GoogleMap) {
            myMap = gMap

            // Sets map position.
            val position = LatLng(23.74575351150717, 90.4112058277774)
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))

            // Sets a marker on the map.
            myMap.addMarker(MarkerOptions().position(position).title("Marker in Dhaka"))








        }
    }