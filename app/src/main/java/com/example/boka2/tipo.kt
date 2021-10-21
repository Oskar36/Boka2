package com.example.boka2

import android.content.Context
import android.content.SharedPreferences

class tipo (context: Context) {
    val PREFS_NAME = "com.example.boka2.sharedpreferences.tipo"
    val SHARED_NAME = "shared_name"
    val Tipo: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var tipo: String
        //comprobamos el archivo de sahred preferences
        get() = Tipo.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Tipo.edit().putString(SHARED_NAME, value).apply()
}