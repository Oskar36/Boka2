package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.p_event_ofe.*

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val iditem = item.getItemId()

        /*if (iditem==R.id.carta){
            val intent=Intent(this, carta::class.java)
            startActivity(intent)
        }*/
        if (iditem==R.id.Localizar){
            val intent= Intent(this, a_localizacion::class.java)
            startActivity(intent)
        }
        if (iditem==R.id.Reservar){
            val intent= Intent(this, a_reservas::class.java)
            startActivity(intent)
        }
        /* if (iditem==R.id.Calendario){
             val intent=Intent(this, ::class.java)
             startActivity(intent)
         }*/
        /*if (iditem==R.id.Quienes){
            val intent=Intent(this, ::class.java)
            startActivity(intent)
        }*/
        if (iditem==R.id.Perfil){
            val intent= Intent(this, a_perfil::class.java)
            startActivity(intent)
        }
        if (iditem==R.id.sesion){

            Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()
            finish()

        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_event_ofe)


        btnhdtpm.setOnClickListener {
            val menu = Intent(this, a_calendario::class.java)
            startActivity(menu)
        }

    }

}