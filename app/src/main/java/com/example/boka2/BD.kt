package com.example.boka2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class usuario (val correo: String, val contraseña: String)
class Base_de_Datos(context:Context, name:String, factory: SQLiteDatabase.CursorFactory?, version:Int) :SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table usuarios (correo text primary key, contraseña text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, NewVersion: Int) {
        db!!.execSQL("drop table if exists usuarios")
        onCreate(db)
    }
    fun insertar(correo:String, contraseña:String ){
        val db=this.writableDatabase
        val fila=ContentValues()
        fila.put("correo",correo)
        fila.put("contraseña",contraseña)
        db.insert("articulos",null,fila)
    }
    fun buscarCorreo(correo1:String):List<usuario>{
        val fila:MutableList<usuario> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where correo=?", arrayOf(correo1))
        while (cursor.moveToNext()){
            val todo= usuario(cursor.getString(0),cursor.getString(1))
            fila.add(todo)
        }

        return fila
    }
    fun ComprobarUsuarion(correo1:String,contraseña1:String):List<usuario>{
        val fila:MutableList<usuario> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where correo=? & contraseña=?", arrayOf(correo1))
        while (cursor.moveToNext()){
            val todo= usuario(cursor.getString(0),cursor.getString(1))
            fila.add(todo)
        }

        return fila
    }
}