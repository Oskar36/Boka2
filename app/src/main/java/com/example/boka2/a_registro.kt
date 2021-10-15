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
            //Comprobamos que no hay ningun campo vacio
            if (txtmail.text.trim().isEmpty() || txtpsw.text.trim().isEmpty() || txtreppaswd.text.trim().isEmpty() ){
                Toast.makeText(this, "${getResources().getString(R.string.campos_vacios)}", Toast.LENGTH_SHORT)
                    .show()
            }else{
                //comprobamos que ambas contraseñas sean iguales
                if (txtpsw.text.toString().equals(txtreppaswd.text.toString())) {
                    //comprobamos la contraseña
                    var ok = 0
                    for (i in 0 until txtpsw.length()){
                        val g = txtpsw.text.toString()
                        val c = g[0]
                        if(c in 'a'..'z' || c in 'A'..'Z' || c in '0'..'9') {
                            ok++
                            if(txtpsw.length()>=6 && ok == 3) {
                                //comprobamos que el usuario no existe
                                if (BBDD.buscarCorreo(txtmail.text.toString())) {
                                    BBDD.insertar(txtmail.text.toString(), txtpsw.text.toString())


                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)

                                }else{
                                    Toast.makeText(this, "${getResources().getString(R.string.usuario_existente)}", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }else{
                            Toast.makeText(this, "${getResources().getString(R.string.Contraseña_incorrecta)}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }else{
                    Toast.makeText(this, "${getResources().getString(R.string.contraseñas_distintas)}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
        

    }
}