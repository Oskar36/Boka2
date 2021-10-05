package com.example.boka2

import android.R.id
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.p_carta.*
import kotlinx.android.synthetic.main.p_carta.view.*
import com.google.android.material.tabs.TabLayout

import android.R.id.tabs
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class a_carta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            super.onCreateOptionsMenu(menu)
            val inflater = menuInflater
            inflater.inflate(R.menu.menu, menu)
            return true
        }
        fun onOptionsItemSelected(item: MenuItem): Boolean {
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_carta)
        tablay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tablay.selectedTabPosition==0){
                   carta()
                }else{
                    oferta()

                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
fun oferta (){

}
fun carta (){

}