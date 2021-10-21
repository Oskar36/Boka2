package com.example.boka2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.p_event_ofe.*

class MainActivity : AppCompatActivity() {

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
            startActivity(intent)



        }
        if (iditem==R.id.Localizar){
            if (Sharedapp.prefs.tipousu.equals("admin")){
                val intent= Intent(this, a_localizaradmin::class.java)
                startActivity(intent)
            }else{
                val intent= Intent(this, a_localizacion::class.java)
                startActivity(intent)
            }

        }
        if (iditem==R.id.Reservar){
            val intent= Intent(this, a_reservas::class.java)
            startActivity(intent)
        }
       if (iditem==R.id.Calendario){
               val intent=Intent(this, a_calendario::class.java)
               startActivity(intent)
           }
        if (iditem==R.id.Quienes){
            val intent=Intent(this, a_quienesSomos::class.java)
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

        SystemClock.sleep(1000)
        val BBDDcarta = Base_de_Datos(this, "carta", null, 1 )
        var context: Context?=null
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_event_ofe)

        val llBotonera = findViewById<View>(R.id.llprincipal) as LinearLayout

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
        val lp = LinearLayout.LayoutParams(
            //LinearLayout.LayoutParams.WRAP_CONTENT,
            //LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams(1125, 700)
        )
        val lp2 = LinearLayout.LayoutParams(
            //LinearLayout.LayoutParams.MATCH_PARENT,
            //LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams(1125, 700)

            )

        val layout: LinearLayout =llprincipal

        //Creamos los botones en bucle
        var numBotones = BBDDcarta.Main().size
        for (i in 0 until numBotones) {
            //Cargamos las imagenes de la base de datos y las añadimos a la vista
            var eveofe:String = BBDDcarta.Main().get(i).nombre
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

