package com.example.boka2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.p_calendario.*
import kotlinx.android.synthetic.main.p_carta.*
import kotlinx.android.synthetic.main.p_event_ofe.*

class a_calendario : AppCompatActivity() {
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
            val intent= Intent(this, a_carta::class.java)
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
            val intent= Intent(this, a_reservas::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.Calendario){
            Toast.makeText(this, "${getResources().getString(R.string.pagina_actual)}", Toast.LENGTH_SHORT).show()
        }
        if (iditem==R.id.Quienes){
            val intent= Intent(this, a_quienesSomos::class.java)
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
    override fun onCreate(savedInstanceState: Bundle?) {

        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_calendario)

        val BBDDeven = Base_de_Datos(this, "bd", null, 1 )
        val llBotonera = findViewById<View>(R.id.llcalendario) as LinearLayout
        var context: Context?=null
        //Creamos las propiedades de layout que tendrán las imagenes.
        //Son LinearLayout.LayoutParams porque las imagenes van a estar en un LinearLayout.
        val lp = LinearLayout.LayoutParams(
            //LinearLayout.LayoutParams.MATCH_PARENT,
            //LinearLayout.LayoutParams.WRAP_CONTENT
            //Asignamos un valor fijo para que las imagenes no se deformen
            LinearLayout.LayoutParams(1125, 700)
        )

        val layout: LinearLayout =llcalendario

        //Creamos las imagenes en bucle
        var numBotones = BBDDeven.Evento_ofe("evento").size
        for (i in 0 until numBotones) {
            //Cargamos las imagenes de la base de datos y las añadimos a la vista
            var eveofe:String = BBDDeven.Evento_ofe("evento").get(i).nombre
            val resID = resources.getIdentifier(eveofe, "drawable", packageName)
            context=this
                val img = ImageView(this)
            lp.setMargins(0,30,0,0)
                img.setLayoutParams(lp)
                img.setBackgroundResource(resID)
                llBotonera.addView(img)
        }
    }
    }
