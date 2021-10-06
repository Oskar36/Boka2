package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.p_registro.*


class a_registro : AppCompatActivity() {
    internal val BBDD = Base_de_Datos(this, "usuarios", null, 1 )

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.logo2)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayUseLogoEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_registro)
        btnRegistro.setOnClickListener() {
            if (txtpsw.text.toString().equals(txtreppaswd.text.toString())) {
                if (BBDD.buscarCorreo(txtmail.text.toString())) {
                    BBDD.insertar(txtmail.text.toString(), txtpsw.text.toString())

                    val intent=Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, ""+BBDD.buscarCorreo(txtmail.text.toString()), Toast.LENGTH_SHORT)
                        .show()

                }
            }else {
                Toast.makeText(this, "Ambas contraseñas deben ser iguales", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        

    }
}