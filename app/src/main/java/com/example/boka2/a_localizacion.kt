package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.p_localizacion.*


class a_localizacion : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map:GoogleMap
    //Barra de tareas
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        if (Sharedapp.prefs.tipousu.equals("invitado")){
            inflater.inflate(R.menu.menuinvitado, menu)
        }else{
            inflater.inflate(R.menu.menu, menu)
        }
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
            Toast.makeText(this, "${getResources().getString(R.string.pagina_actual)}", Toast.LENGTH_SHORT).show()

        }
        if (iditem==R.id.Reservar){
            val intent= Intent(this, a_reservas::class.java)
            finish()
            startActivity(intent)
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
            if (!Sharedapp.prefs.tipousu.equals("invitado")){
                val intent= Intent(this, a_perfil::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_login::class.java)
                finish()
                startActivity(intent)
            }

        }
        if (iditem==R.id.sesion){

            if (!Sharedapp.prefs.tipousu.equals("invitado")){
                Toast.makeText(this,  "${getResources().getString(R.string.cierre_sesion)}", Toast.LENGTH_SHORT).show()
                Sharedapp.prefs.tipousu = "invitado"
                val intent= Intent(this, a_login::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_registro::class.java)
                Sharedapp.prefs.tipousu = "invitado"
                finish()
                startActivity(intent)
            }
        }

        return true
    }
    private var mapView: MapView? = null
    private var gmap: GoogleMap? = null
    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_localizacion)
        //Barra de tareas
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)
        //En caso de que haya problemas con el Bundle
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        mapView = mapView4
        mapView!!.onCreate(mapViewBundle)
        mapView!!.getMapAsync(this)


    }
    //En caso de que haya problemas con el Bundle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView!!.onSaveInstanceState(mapViewBundle)
    }
    //Funciones obligatorias de tener para que le mapa funcione
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
        super.onPause()
        mapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
        gmap!!.setMinZoomPreference(15f)
        val ny = LatLng(43.267010, -2.942118)
        val location = LatLng(43.267010, -2.942118)
        gmap!!.moveCamera(CameraUpdateFactory.newLatLng(ny))
        //Definición del marcador y ponerle la foto al marcador
        val marker = MarkerOptions().position(ny)
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.furgomapa))
        gmap!!.addMarker(marker)
    }
}

