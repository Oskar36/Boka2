package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class a_reservas : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val iditem = item.getItemId()

        if (iditem==R.id.carta){
            val intent=Intent(this, a_carta::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.Localizar){
            val intent= Intent(this, a_localizacion::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.Reservar){
            Toast.makeText(this, "Ya estas en esta pagina", Toast.LENGTH_SHORT).show()
        }
        if (iditem==R.id.Calendario){
               val intent=Intent(this, a_calendario::class.java)
            finish()
               startActivity(intent)
           }
        if (iditem==R.id.Quienes){
            val intent=Intent(this, a_quienesSomos::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.Perfil){
            val intent= Intent(this, a_perfil::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.sesion){


            Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()
            val intent= Intent(this, a_login::class.java)
            finish()
            startActivity(intent)
        }

        return true
    }
    private var mapView: MapView? = null
    private var gmap: GoogleMap? = null
    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"
    override fun onCreate(savedInstanceState: Bundle?) {

        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_reservas)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        mapView = findViewById(R.id.mapView3)
        mapView!!.onCreate(mapViewBundle)
        mapView!!.getMapAsync(this)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView!!.onSaveInstanceState(mapViewBundle)
    }
    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onPause() {
        mapView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView!!.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }
//prueba
    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
        gmap!!.setMinZoomPreference(12f)
        val location = LatLng(43.267010, -2.942118)
        val ny = LatLng(43.267010, -2.942118)
        gmap!!.moveCamera(CameraUpdateFactory.newLatLng(ny))
    gmap!!.addMarker(MarkerOptions().position(location))
    }


}