package com.example.boka2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class usuario (val correo: String, val contraseña: String)
data class eve_ofe(val titulo:String,val fecha:String,val tipo: String,val info:String, val img:String)
data class carta (val nombre:String, val tipo:String,val precio:Int,val info:String,val img:String,val alergias:String)
data class localizacion (val calle: String, val municipio: String)
class Base_de_Datos(context:Context, name:String, factory: SQLiteDatabase.CursorFactory?, version:Int) :SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table usuarios (correo text primary key, contraseña text)")
        db!!.execSQL("create table eventos_ofertas ( titulo text,fecha text,tipo text, img text,primary key(titulo,fecha))  ")
        db!!.execSQL("create table carta (nombre text primary key, tipo text,precio real,info text,img text,alergias text)")
        db!!.execSQL("create table localizacion (calle text primary key, municipio text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, NewVersion: Int) {
        db!!.execSQL("drop table if exists usuarios")
        db!!.execSQL("drop table if exists eventos_ofertas")
        db!!.execSQL("drop table if exists carta")
        db!!.execSQL("drop table if exists localizacion")
        onCreate(db)
    }
    fun insertarUsurario(correo:String, contraseña:String ){
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
    fun ComprobarUsuario(correo1:String,contraseña1:String):List<usuario>{
        val fila:MutableList<usuario> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where correo=? & contraseña=?", arrayOf(correo1))
        while (cursor.moveToNext()){
            val todo= usuario(cursor.getString(0),cursor.getString(1))
            fila.add(todo)
        }
        return fila
    }
    fun Eventos(evento:String):List<eve_ofe>{
        val fila:MutableList<eve_ofe> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where tipo='Evento' ", arrayOf(evento))
        while (cursor.moveToNext()){
            val todo= eve_ofe(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4))
            fila.add(todo)
        }
        return fila
    }
    fun Ofertas(oferta:String):List<eve_ofe>{
        val fila:MutableList<eve_ofe> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where tipo='Oferta' ", arrayOf(oferta))
        while (cursor.moveToNext()){
            val todo= eve_ofe(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4))
            fila.add(todo)
        }
        return fila
    }
    fun Carta(carta:String):List<carta>{
        val fila:MutableList<carta> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where tipo=? ", arrayOf(carta))
        while (cursor.moveToNext()){
            val todo= carta(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(6))
            fila.add(todo)
        }
        return fila
    }
    fun Municipio(municip:String):List<localizacion>{
        val fila:MutableList<localizacion> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where municipio=? ", arrayOf(municip))
        while (cursor.moveToNext()){
            val todo= localizacion(cursor.getString(0),cursor.getString(1))
            fila.add(todo)
        }
        return fila
    }
    fun Calle(municip:String,calle:String):List<localizacion>{
        val fila:MutableList<localizacion> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select * where municipio=municip ", arrayOf(calle))
        while (cursor.moveToNext()){
            val todo= localizacion(cursor.getString(0),cursor.getString(1))
            fila.add(todo)
        }
        return fila
    }
}