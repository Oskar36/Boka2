package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.gms.maps.GoogleMap
import kotlinx.android.synthetic.main.p_localizacion.mapView4
import kotlinx.android.synthetic.main.p_localizaradmin.*
import kotlinx.android.synthetic.main.p_reservas.*
import java.text.SimpleDateFormat
import java.util.*


class a_localizaradmin : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map:GoogleMap
    //Barra de tareas
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
            val intent= Intent(this, a_perfil::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.sesion){

            Toast.makeText(this, "${getResources().getString(R.string.cierre_sesion)}", Toast.LENGTH_SHORT).show()
            Sharedapp.prefs.tipousu = "invitado"
            Sharedapp.user.user = ""
            Sharedapp.paswd.paswd = ""
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_localizaradmin)
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
        txtFechaADM.setOnClickListener(){
            showDatePickerDialog(1)
        }
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
        gmap!!.setMinZoomPreference(10f)
        val bd = Base_de_Datos(this, "bd", null, 1)
        val lista3 = bd.Localizacion()
       for(i in 0 until bd.Localizacion().size){
           val location = LatLng(bd.Localizacion().get(i).coordenada1, bd.Localizacion().get(i).coordenada2)
           val marker = MarkerOptions().position(location).title(bd.Localizacion().get(i).municipio).snippet(bd.Localizacion().get(i).calle)
           marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.furgomapa))
           gmap!!.moveCamera(CameraUpdateFactory.newLatLng(location))
           gmap!!.addMarker(marker)
       }
        gmap!!.setOnMarkerClickListener { marker ->
            if (marker.isInfoWindowShown) {
                marker.hideInfoWindow()
            } else {
                marker.showInfoWindow()
            }
            val sdf = SimpleDateFormat("dd/M/yyyy ")
            val currentDate = sdf.format(Date())
            val txtFechaADM = findViewById<EditText>(R.id.txtFechaADM) as TextView
            txtFechaADM.text=currentDate.toString()
            TxtComensales.text="Comensales: "+bd.buscarReserva(marker.snippet.toString(),txtFechaADM.text.toString()).toString()
            true
        }
    }
    private fun showDatePickerDialog(elementId: Int) {
        val datePicker = DatePickerFragment { day, month, year ->
            onDateSelected(day, month, year,elementId) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int,
                               elementoPicker: Int) {
        val txtFechaRes = findViewById<View>(R.id.txtFechaADM) as TextView
        txtFechaRes.setText("$day/${month+1}/$year")
    }
}


