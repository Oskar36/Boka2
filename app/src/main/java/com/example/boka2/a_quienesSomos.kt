package com.example.boka2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.p_quienes_somos.*

class a_quienesSomos : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val iditem = item.getItemId()

        if (iditem==R.id.carta){
            val intent=Intent(this, a_carta::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.Localizar){
            if (Sharedapp.prefs.tipousu.equals("admin")){
                val intent= Intent(this, a_localizaradmin::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_localizacion::class.java)
                finish()
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
            val intent=Intent(this, a_quienesSomos::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.Perfil){
            val intent= Intent(this, a_perfil::class.java)
            finish()
            startActivity(intent)
        }
        if (iditem==R.id.sesion){

            if (!Sharedapp.prefs.tipousu.equals("invitado")){
                Toast.makeText(this,  "${getResources().getString(R.string.cierre_sesion)}", Toast.LENGTH_SHORT).show()
                Sharedapp.prefs.tipousu = "invitado"
                Sharedapp.user.user = ""
                Sharedapp.paswd.paswd = ""
                val intent= Intent(this, a_login::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_registro::class.java)
                Sharedapp.prefs.tipousu = "invitado"
                finish()
                startActivity(intent)
            }
        }

        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_quienes_somos)
        //Al pulsar los diferentes txt nos redirige a sus correspondientes formas de contacto (Telefono, instagram.....)
        txtInstagram.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/?hl=es"))
            startActivity(browserIntent)
        }
        txtFacebook.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://es-es.facebook.com/"))
            startActivity(browserIntent)
        }
        txtGmail.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin"))
            startActivity(browserIntent)
        }
        txtTwitter.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/?lang=es"))
            startActivity(browserIntent)
        }
        txtTelefono.setOnClickListener(){
            val nTel= Uri.parse("tel:"+txtTelefono.text.toString())
            val phone= Intent(Intent.ACTION_DIAL, nTel)
            startActivity(phone)
        }
    }
}