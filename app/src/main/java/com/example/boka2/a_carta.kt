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
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.p_calendario.*
import kotlinx.android.synthetic.main.p_carta.*

class a_carta : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val iditem = item.getItemId()

        if (iditem==R.id.carta){
            Toast.makeText(this, "Ya estas en esta pagina", Toast.LENGTH_SHORT).show()
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
               val intent=Intent(this, a_calendario::class.java)
              finish()
               startActivity(intent)
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

            Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()

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
        setContentView(R.layout.p_carta)
        val llBotonera = findViewById<View>(R.id.llcarta) as LinearLayout
        var context:Context?=null
        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
            //LinearLayout.LayoutParams(1125, 700)
        )
        val lp2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,


            )

        val layout: LinearLayout =llcarta

        //Creamos los botones en bucle
        for (i in 0 until numBotones) {
            context=this
            val img = ImageView(this)
            val img1 = ImageView(this)
            img.setLayoutParams(lp)
            img1.setLayoutParams(lp2)
            img1.setBackgroundResource(R.drawable.carta1)
            llBotonera.addView(img1)
        }




        tablay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                llBotonera.removeAllViews()
                if (tablay.selectedTabPosition==0){
                    for (i in 0 until numBotones) {
                        val img = ImageView(context)
                        img.setLayoutParams(lp)
                        img.setBackgroundResource(R.drawable.carta1)
                        llBotonera.addView(img)
                    }

                }else{
                    for (i in 0 until numBotones) {
                        val img1 = ImageView(context)
                        img1.setLayoutParams(lp2)
                        img1.setBackgroundResource(R.drawable.oferta1)
                        llBotonera.addView(img1)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    companion object {
        var numBotones = 5
    }
    }
