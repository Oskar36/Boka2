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
import android.content.SharedPreferences
import android.os.SystemClock
import android.widget.TextView



class a_login : AppCompatActivity() {
    //1
    private val BBDD = Base_de_Datos(this, "usuarios", null, 1 )

    override fun onCreate(savedInstanceState: Bundle?) {

        SystemClock.sleep(1000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_login)
        if (Sharedapp.prefs.tipousu.equals("cliente") || Sharedapp.prefs.tipousu.equals("admin")){
            val persistencia = Intent(this, MainActivity::class.java)
            finish()
            startActivity(persistencia)
        }


        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)



        Login_txt_registro.setOnClickListener(){

            //En caso de pulsar el  boton de registro  nos redirige a dicha activity

            val intent= Intent(this, a_registro::class.java)
            startActivity(intent)

        }
        Login_but_entrar.setOnClickListener() {
            val login_correo = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.login_correo) as TextView
            val login_contr = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.login_contr) as TextView

            //Si introducimos los datos de inicio de sesion de un administrador
            if (login_correo.text.toString().toLowerCase().equals("admin") && login_contr.text.toString().toLowerCase().equals("admin")){
                val intent =Intent(this, MainActivity::class.java)

                //Si iciamos sesion como cliente
            }else {
                if (BBDD.ComprobarUsuario(login_correo.text.toString(), login_contr.text.toString())) {
                    val intent = Intent(this, MainActivity::class.java)

                    //En caso de que los datos introducidos no correspondan a ningun usuario registrado
                } else {
                    Login_txt_error.text = "${getResources().getString(R.string.usuarioycontraseñaincorrectos)}"
                }
            }
        }
        //Si pulsamos el boton de entrar como invitado nos manda a la pantalla principal como invitados
        txtInvitado.setOnClickListener(){
            val invitado = Intent(this, MainActivity::class.java)
            Sharedapp.prefs.tipousu = "invitado"
            finish()
            startActivity(invitado)
        }
    }
}