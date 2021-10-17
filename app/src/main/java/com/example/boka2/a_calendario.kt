package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.p_calendario.*

class a_calendario : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        if (Sharedapp.prefs.tipousu.equals("invitado")){
            inflater.inflate(R.menu.menuinvitado, menu)
        }else{
            inflater.inflate(R.menu.menu, menu)
        }
        //inflater.inflate(R.menu.menu, menu)
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
                startActivity(intent)
            }else{
                val intent= Intent(this, a_localizacion::class.java)
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
            val intent= Intent(this, a_perfil::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.sesion){

            Toast.makeText(this,  "${getResources().getString(R.string.cierre_sesion)}", Toast.LENGTH_SHORT).show()
            Sharedapp.prefs.tipousu = "invitado"
            val intent= Intent(this, a_login::class.java)
            finish()
            startActivity(intent)
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




        val llBotonera = findViewById<View>(R.id.llcalendario) as LinearLayout

        //Creamos las propiedades de layout que tendrán las imagenes.
        //Son LinearLayout.LayoutParams porque las imagenes van a estar en un LinearLayout.
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
            //LinearLayout.LayoutParams(1125, 700)
        )
        val lp2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,


            )

        val layout: LinearLayout =llcalendario

        //Creamos los botones en bucle
        for (i in 0 until numBotones) {
            val img = ImageView(this)
            val txt = TextView(this)
            txt.setLayoutParams(lp2)
            img.setLayoutParams(lp)
            //Asignamos Texto al botón
            img.setBackgroundResource(R.drawable.evento1)
            //Añadimos el botón a la botonera
            llBotonera.addView(img)
            llBotonera.addView(txt)

        }
    }

    companion object {
        var numBotones = 5
    }

    }
