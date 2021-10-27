package com.example.boka2

import android.content.Context
import android.content.SharedPreferences

class Reserva(context: Context) {
    val PREFS_NAME = "com.example.boka2.sharedpreferences.ReservE"
    val SHARED_NAME = "shared_name"
    val Reserva: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var reservas: String
        //comprobamos el archivo de sahred preferences
        get() = Reserva.getString(SHARED_NAME, "").toString()
        //modificamos el valor al shared preferences
        set(value) = Reserva.edit().putString(SHARED_NAME, value).apply()
}