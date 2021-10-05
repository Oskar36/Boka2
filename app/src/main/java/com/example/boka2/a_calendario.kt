package com.example.boka2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

import android.R.id

import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.p_calendario.*


class a_calendario : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Establecemos el layout main
        setContentView(R.layout.p_calendario)

        //Obtenemos el linear layout donde colocar los botones
        val llBotonera = findViewById<View>(R.id.llcalendario) as LinearLayout

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
        val lp = LinearLayout.LayoutParams(
            //LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams(1125, 700)
        )
            val lp2 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,


        )
    val layout:LinearLayout=l_layout

        //Creamos los botones en bucle
        for (i in 0 until numBotones) {
            val img = ImageView(this)
            val txt = TextView(this)
            txt.setLayoutParams(lp2)
            img.setLayoutParams(lp)
            //Asignamos Texto al botón
            txt.setText("Boton " + String.format("%02d", i))
            img.setBackgroundResource(R.drawable.instagram)
            //Añadimos el botón a la botonera
            llBotonera.addView(img)
            llBotonera.addView(txt)

        }
    }

    companion object {
        var numBotones = 5
    }
}