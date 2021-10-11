package com.example.boka2

import android.app.Application

class Sharedapp : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }

}
