package com.example.boka2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.p_login.*
import kotlinx.android.synthetic.main.p_registro.*
import android.content.SharedPreferences




class a_login : AppCompatActivity() {
    private val BBDD = Base_de_Datos(this, "usuarios", null, 1 )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_login)

        Login_txt_registro.setOnClickListener(){


            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setLogo(R.drawable.logo2)
            supportActionBar?.title = ""
            supportActionBar?.setDisplayUseLogoEnabled(true)

            val intent= Intent(this, a_registro::class.java)
            startActivity(intent)


        }
        Login_but_entrar.setOnClickListener() {

            if (login_correo.text.toString().toLowerCase().equals("admin") && login_contr.text.toString().toLowerCase().equals("admin")){
                val intent=Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }else {
                if (BBDD.ComprobarUsuario(
                        login_correo.text.toString(),
                        login_contr.text.toString()
                    )
                ) {
                    //tipousu = "invitado"
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Login_txt_error.text = "El usuario o la contraseña no son correctos"
                }
            }
        }
    }
}