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
            //Si alguno de los campos estan vacios
            if (txtmail.text.trim().isEmpty() || txtpsw.text.trim().isEmpty() || txtreppaswd.text.trim().isEmpty() ){
                Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT)
                    .show()
                //si no estan vacios
            }else{
                //La primera de contraseña y la segunda deben ser iguales
                if (txtpsw.text.toString().equals(txtreppaswd.text.toString())) {
                    //Si el usuario no existe en la BBDD
                    if (BBDD.buscarCorreo(txtmail.text.toString())) {
                        BBDD.insertar(txtmail.text.toString(), txtpsw.text.toString())


                        val intent=Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    //Si el usuario ya existe
                    } else {
                        txterror.text = "El usuario ya existe"
                     }
                    //Si las contraseñas no son iguales
                }else {
                    txterror.text = "Ambas contraseñas deben ser iguales"

                }
            }

        }
        

    }
}