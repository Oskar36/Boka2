package com.example.boka2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.p_event_ofe.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.p_event_ofe)
        btnhdtpm.setOnClickListener {
            val menu = Intent(this, a_localizacion::class.java)
            startActivity(menu)
        }
    }
}