package com.example.boka2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.p_reservas.*
import android.widget.TextView
import androidx.core.view.isEmpty
import kotlinx.android.synthetic.main.custom_dialog.*
import android.app.Activity

import android.content.ContextWrapper





class a_reservas : AppCompatActivity(), OnMapReadyCallback, NumberPicker.OnValueChangeListener {
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
            if (Sharedapp.prefs.tipousu.equals("admin")){
                val intent= Intent(this, a_localizaradmin::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_localizacion::class.java)
                finish()
                startActivity(intent)
            }

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

        //Barra de tareas
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)




        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_reservas)
        //En caso de que haya problemas con el Bundle
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        mapView = findViewById(R.id.mapView3)
        try {
            mapView!!.onCreate(mapViewBundle)
        }
        catch (e: Exception){
            Toast.makeText(this, "${getResources().getString(R.string.mapa_error)}", Toast.LENGTH_SHORT).show()
        }
        mapView!!.getMapAsync(this)
        //numbrerpicker

        numberPicker?.wrapSelectorWheel = false
        if (numberPicker != null) {
            numberPicker.minValue = 0
            numberPicker.maxValue = 20
        }
        txtFechaRes.setOnClickListener(){
            showDatePickerDialog(1)
        }
        txtHoraRes.setOnClickListener(){

            showTimePickerDialog(1)
        }



        // spinners
        val bd = Base_de_Datos(this, "bd", null, 1)
        val lista1 = bd.Municipio()
        val context:Context = this
        val adaptador1 = ArrayAdapter (this, android.R.layout.simple_spinner_item, lista1)
        spinermunicipio.adapter=adaptador1


        spinermunicipio.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val municipio = spinermunicipio.getSelectedItem().toString()
                val sacarCalle = bd.Calle(municipio)
                val adaptador2 = ArrayAdapter (context, android.R.layout.simple_spinner_item, sacarCalle)
                spinercalle.adapter=adaptador2
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spinercalle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
               // bd.Coordenadas()
                gmap!!.clear()
                val location = LatLng(bd.Coordenadas(spinercalle.selectedItem.toString()).get(0).coorde1, bd.Coordenadas(spinercalle.selectedItem.toString()).get(0).coorde2)
                val marker = MarkerOptions().position(location)
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.furgomapa))
                gmap!!.moveCamera(CameraUpdateFactory.newLatLng(location))
                gmap!!.addMarker(marker)

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        btnreserva.setOnClickListener(){
            val textofecha= findViewById<View>(R.id.txtFechaRes) as TextView
            val textohora= findViewById<View>(R.id.txtHoraRes) as TextView
            //Si entra como invitado aparecera un mensaje debido a que debe iniciar sesion
          if(!Sharedapp.prefs.tipousu.equals("invitado")){
              //Comprobar si los campos no estan vacios
            if(!textohora.text.toString().isEmpty() && !textofecha.text.toString().isEmpty()  && !numberPicker.isEmpty()){
                Toast.makeText(this, "Reserva realizada correctamente", Toast.LENGTH_SHORT).show()
                bd.insertReserva(textofecha.text.toString(),textohora.text.toString(),numberPicker.value.toString(),spinermunicipio.selectedItem.toString(),spinercalle.selectedItem.toString())
                val intent= Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }else{

                Toast.makeText(this, "${getResources().getString(R.string.campos_vacios)}", Toast.LENGTH_SHORT).show()

            }
          }else{
              CustomDialogClass(this).show()
              Sharedapp.reserva.reservas="si"
          }
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
}
    private fun showTimePickerDialog(elementId: Int) {
        val timePicker = TimePickerFragment { onTimeSelected(it, elementId) }
        timePicker.show(supportFragmentManager, "timePicker")
    }
    private fun showDatePickerDialog(elementId: Int) {
        val datePicker = DatePickerFragment { day, month, year ->
            onDateSelected(day, month, year,elementId) }
        datePicker.show(supportFragmentManager, "datePicker")
    }
    private fun onTimeSelected(time: String, elementoPicker: Int) {
            val txtHoraRes = findViewById<View>(R.id.txtHoraRes) as TextView
            txtHoraRes.setText("$time")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int,
                               elementoPicker: Int) {
        val txtFechaRes = findViewById<View>(R.id.txtFechaRes) as TextView
        txtFechaRes.setText("$day/$month/$year")
    }
    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
    }



}