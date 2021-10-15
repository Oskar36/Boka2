package com.example.boka2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//Clases para almacenar los datos de la base de datos
data class usuario (val correo: String, val contraseña: String)
data class eve_ofe(val titulo:String,val fecha:String,val tipo: String,val info:String, val img:String)
data class carta (val nombre:String, val tipo:String,val alergias:String)
data class cartagen(val nombre:String)
data class localizacion (val calle: String, val municipio: String)
class Base_de_Datos(context:Context, name:String, factory: SQLiteDatabase.CursorFactory?, version:Int) :SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table usuarios (user text primary key, contrasena text)")
        db!!.execSQL("create table eventos_ofertas ( titulo text,fecha text,tipo text, img text,primary key(titulo,fecha))  ")
        db!!.execSQL("create table carta (nombre text primary key, tipo text,alergias text)")
        db!!.execSQL("create table localizacion (calle text primary key, municipio text)")
        //Insercciones de la tabla carta
        insertarCarta("ensalada","general","ninguno",db)
        insertarCarta("fajitas","general","ninguno",db)
        insertarCarta("sandwich","general","ninguno",db)
        insertarCarta("smoothie","general","ninguno",db)
        insertarCarta("smoothiebowl","general","ninguno",db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, NewVersion: Int) {
        db!!.execSQL("drop table if exists eventos_ofertas")
        db!!.execSQL("drop table if exists carta")
        db!!.execSQL("drop table if exists localizacion")
        onCreate(db)
    }
    //Insercción del usuario en la base de datos
    fun insertar(usuario:String, contraseña:String){
        val db=this.writableDatabase
        val registrar=ContentValues()
        registrar.put("user",usuario)
        registrar.put("contrasena",contraseña)
        db.insert("usuarios",null,registrar)
    }
    //Insercción de elemento de la carta en la base de datos
    fun insertarCarta(nombre1:String,tipo1:String,alergias1: String, db:SQLiteDatabase?){

    val registrar2=ContentValues()
    registrar2.put("nombre",nombre1)
    registrar2.put("tipo",tipo1)
    registrar2.put("alergias",alergias1)
    db?.insert("carta",null,registrar2)
}
    //Funcion para buscar el correo
    fun buscarCorreo(user1:String):Boolean{
        val fila:MutableList<usuario> = ArrayList()
        val db=this.readableDatabase
        val cursorfila = db.rawQuery("select * from usuarios where user=?", arrayOf(user1))
        if (cursorfila.count == 0){
            return true


        }else{
            return false

        }

    }
    //Funcion para comprobar usuario
    fun ComprobarUsuario(user1:String,contrasena1:String):Boolean{
        val fila:MutableList<usuario> = ArrayList()
        val db=this.readableDatabase
        val cursorfila = db.rawQuery("select * from usuarios where user=? AND contrasena=?", arrayOf(user1, contrasena1))
        if (cursorfila.count == 1){
            return true
        }else{
            return false
        }
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
    //Funcion para recoger en una lista todos los tipo de alimentos que hay en la carta
    fun Carta(tipo:String):List<cartagen>{
        val fila:MutableList<cartagen> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select nombre from carta where tipo=? ", arrayOf(tipo))
        while (cursor.moveToNext()){
            val todo= cartagen(cursor.getString(0))
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