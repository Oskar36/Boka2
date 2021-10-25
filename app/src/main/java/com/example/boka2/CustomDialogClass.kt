package com.example.boka2

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.custom_dialog.*
import android.app.Activity
import android.content.ContextWrapper
import android.view.ContextThemeWrapper


class CustomDialogClass(context: Context): Dialog(context) {

    init {
        setCancelable(true)
    }
    var contexto:Context?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
    btn_iniciar_sesion.setOnClickListener(){
        this.dismiss()
        val activity = Intent(context, a_login::class.java)
        context.startActivity(activity)
        }
    }
}