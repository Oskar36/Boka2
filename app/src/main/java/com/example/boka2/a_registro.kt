package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.p_login.*
import kotlinx.android.synthetic.main.p_registro.*


class a_registro : AppCompatActivity() {

    internal val BBDD = Base_de_Datos(this, "bd", null, 1 )

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.logo2)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayUseLogoEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_registro)
        btnRegistro.setOnClickListener() {
            val txtmail = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.txtmail) as TextView
            val txtpsw = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.txtpsw) as TextView
            val txtreppaswd = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.txtreppaswd) as TextView
            //Comprobamos que no hay ningun campo vacio
            if (!txtmail.text.trim().isEmpty() && !txtpsw.text.trim().isEmpty() && !txtreppaswd.text.trim().isEmpty() ){
                if (txtpsw.text.toString().equals(txtreppaswd.text.toString())) {
                    //comprobamos la contraseña (alfanumerica minimo de 6 caracteres combinando mayusculas y minusculas)
                    var ok = 0
                    var i=0
                    var mayus=false
                    var numero=false
                    if(txtpsw.text.toString().length>=6){
                            while(i<txtpsw.text.toString().length || (!mayus && !numero)){
                                val g = txtpsw.text.toString()
                                val c = g.get(index = i)
                                if(c in 'A'..'Z') {
                                    mayus=true
                                }
                                if( c in '0'..'9'){
                                    numero=true
                                }

                                i++
                            }
                            if(mayus && numero){
                                if (BBDD.buscarCorreo(txtmail.text.toString())){
                                    val intent = Intent(this, MainActivity::class.java)

                                    Toast.makeText(this, "Usuario creado con exito", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                else{
                                    Toast.makeText(this, "${getResources().getString(R.string.usuario_existente)}", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                            else{
                                Toast.makeText(this, "${getResources().getString(R.string.Contraseña_incorrecta)}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                    else{
                        Toast.makeText(this, "${getResources().getString(R.string.Contraseña_incorrecta)}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }else{
                    Toast.makeText(this, "${getResources().getString(R.string.contraseñas_distintas)}", Toast.LENGTH_SHORT)
                        .show()
                }
            }else{
                //comprobamos que ambas contraseñas sean iguales
                Toast.makeText(this, "${getResources().getString(R.string.campos_vacios)}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        

    }

}