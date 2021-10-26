package com.example.boka2

import android.content.Context
import android.content.SharedPreferences

class Fecha(context: Context) {
    val PREFS_NAME = "com.example.boka2.sharedpreferences.Fecha"
    val SHARED_NAME = "shared_name"
    val Fecha: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var fecha: String
        //comprobamos el archivo de sahred preferences
        get() = Fecha.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Fecha.edit().putString(SHARED_NAME, value).apply()
}