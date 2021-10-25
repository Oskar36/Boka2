package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.p_perfil.*
import kotlinx.android.synthetic.main.p_registro.*

class a_perfil : AppCompatActivity() {
    //Barra de tareas
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
            Toast.makeText(this, "${getResources().getString(R.string.pagina_actual)}", Toast.LENGTH_SHORT).show()
            if (!Sharedapp.prefs.tipousu.equals("invitado")){
                val intent= Intent(this, a_perfil::class.java)
                finish()
                startActivity(intent)
            }else{
                val intent= Intent(this, a_login::class.java)
                finish()
                startActivity(intent)
            }

        }
        if (iditem==R.id.sesion){

            Toast.makeText(this, "${getResources().getString(R.string.cierre_sesion)}", Toast.LENGTH_SHORT).show()
            Sharedapp.prefs.tipousu = "invitado"
            val intent= Intent(this, a_login::class.java)
            finish()
            startActivity(intent)
        }

        return true
    }
    private val BBDD = Base_de_Datos(this, "bd", null, 1 )
    var usuario:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
//Barra de tareas
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo2)
        getSupportActionBar()?.setTitle("")
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_perfil)
        prefil_txt_correo.setText(Sharedapp.user.user)
        Perfil_contra.setText(Sharedapp.paswd.paswd)
        usuario= prefil_txt_correo.text.toString()
        Perfil_btn_guardar.setOnClickListener {
            if (!prefil_txt_correo.text.isEmpty() &&  !Perfil_contra.text.isEmpty()){
                    //comprobamos la contraseña
                    var ok = 0
                    var i=0
                    var mayus=false
                    var numero=false
                    if(Perfil_contra.text.toString().length>=6){
                        val g = Perfil_contra.text.toString()
                        while(i<Perfil_contra.text.toString().length || (!mayus && !numero)){

                            val c = g.get(i)
                            if(c in 'A'..'Z') {
                                mayus=true
                            }
                            if( c in '0'..'9'){
                                numero=true
                            }
                            i++
                        }
                        if(mayus && numero){

                                BBDD.actualizar(usuario,  prefil_txt_correo.text.toString(), Perfil_contra.text.toString())
                                usuario= prefil_txt_correo.text.toString()
                                Toast.makeText(this, "Datos del usuario actualizados", LENGTH_SHORT).show()
                                Sharedapp.paswd.paswd = Perfil_contra.text.toString()
                                Sharedapp.user.user = prefil_txt_correo.text.toString()
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
                    Toast.makeText(this, "${getResources().getString(R.string.campos_vacios)}", Toast.LENGTH_SHORT)
                        .show()
                }
        }



    }
}