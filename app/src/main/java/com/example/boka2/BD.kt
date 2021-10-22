package com.example.boka2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//Clases para almacenar los datos de la base de datos
data class usuario (val correo: String, val contraseña: String)
data class carta (val nombre:String, val tipo:String,val alergias:String)
data class cartagen(val nombre:String)
data class eve_ofe(val nombre:String)
data class coordenadas(val coorde1:Double,val coorde2:Double)
data class coordenadas2(val calle:String,val coorde1:String,val coorde2:String)
data class localizacion (val calle: String, val municipio: String, val coordenada1: String, val coordenada2: String)
class Base_de_Datos(context:Context, name:String, factory: SQLiteDatabase.CursorFactory?, version:Int) :SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table usuarios (user text primary key, contrasena text)")
        db!!.execSQL("create table eveofe ( nombre text,fecha real,tipo text,primary key(nombre,fecha))  ")
        db!!.execSQL("create table carta (nombre text primary key, tipo text,alergias text)")
        db!!.execSQL("create table localizacion (municipio text, calle text primary key ,coordenada1 text, coordenada2 text)")
        db!!.execSQL("create table reserva (id text primary key,fecha text, hora text,comensales text,municipio text,calle text)")
      //  db!!.execSQL("create table reserva (id text  primary key AUTOINCREMENT, fecha text,hora text,comensales text,municipio text, calle text)")
        //Insercciones de la tabla carta
        insertarCarta("ensalada","general","ninguno",db)
        insertarCarta("tormenta","ensalada","ninguno",db)
        insertarCarta("tata_doc","ensalada","ninguno",db)
        insertarCarta("salvaje","ensalada","ninguno",db)
        insertarCarta("okinawa","ensalada","ninguno",db)
        insertarCarta("natural","ensalada","ninguno",db)
        insertarCarta("alabama","ensalada","ninguno",db)
        insertarCarta("fajitas","general","ninguno",db)
        insertarCarta("vegetal","fajita","ninguno",db)
        insertarCarta("espinacas","fajita","ninguno",db)
        insertarCarta("arroz","fajita","ninguno",db)
        insertarCarta("aguacate","fajita","ninguno",db)
        insertarCarta("sandwich","general","ninguno",db)
        insertarCarta("sueco","sandwich","ninguno",db)
        insertarCarta("noruega","sandwich","ninguno",db)
        insertarCarta("mixto","sandwich","ninguno",db)
        insertarCarta("mexico","sandwich","ninguno",db)
        insertarCarta("huerta","sandwich","ninguno",db)
        insertarCarta("guayana","sandwich","ninguno",db)
        insertarCarta("smoothie","general","ninguno",db)
        insertarCarta("vitaly","smoothie","ninguno",db)
        insertarCarta("pineapple","smoothie","ninguno",db)
        insertarCarta("colada_jungle","smoothie","ninguno",db)
        insertarCarta("caribbean","smoothie","ninguno",db)
        insertarCarta("smoothiebowl","general","ninguno",db)
        insertarCarta("tropical","smoothiebowl","ninguno",db)
        insertarCarta("holi","smoothiebowl","ninguno",db)
        insertarCarta("green","smoothiebowl","ninguno",db)
        insertarCarta("banana","smoothiebowl","ninguno",db)



        //inserciones de eventos y ofertas
        insertarEventoOfe("aitana","2022-10-16","evento",db)
        insertarEventoOfe("bbk","2022-10-07","evento",db)
        insertarEventoOfe("espiritus","2022-01-08","evento",db)
        insertarEventoOfe("gatibu","2022-01-19","evento",db)
        insertarEventoOfe("justin","2022-10-22","evento",db)
        insertarEventoOfe("alejandro","2022-10-11","evento",db)
        insertarEventoOfe("manuel","2022-02-12","evento",db)
        insertarEventoOfe("ofertaa","2022-01-17","oferta",db)
        insertarEventoOfe("ofertab","2022-01-18","oferta",db)
        insertarEventoOfe("ofertac","2022-01-14","oferta",db)
        insertarEventoOfe("ofertad","2022-01-13","oferta",db)
        insertarEventoOfe("ofertae","2022-01-12","oferta",db)
        insertarEventoOfe("ofertaf","2022-01-29","oferta",db)


        insertarLocalizacion ("Bilbao","Pl. del Arriaga","43.259572","-2.925205",db)
        insertarLocalizacion ("Bilbao","Av. Abandoibarra","43.268270","-2.933497",db)
        insertarLocalizacion ("Bilbao","Indautxu Plaza","43.260.181","-2.940444",db)
        insertarLocalizacion ("Bilbao","Av. Askatasuna","43.251758","-2.926630",db)
        insertarLocalizacion ("Bilbao","Calle Luis Briñas","43.263514","-2.947154",db)
        insertarLocalizacion ("Barakaldo","C. el Retiro","43.290490","-2.988381",db)
        insertarLocalizacion ("Barakaldo","Av. la Ribera","43.292164","-3.006629",db)
        insertarLocalizacion ("Barakaldo","La Torre Kalea","43.291513","-2.999470",db)
        insertarLocalizacion ("Barakaldo","P.º El Ferrocarril","43.302406","-2.987386",db)
        insertarLocalizacion ("Basauri","Artunduaga Etxadia","43.233101","-2.881716",db)
        insertarLocalizacion ("Basauri","C. Basozelai","43.235204","-2.891742",db)
        insertarLocalizacion ("Portugalete","Mlle. Tomás Olabarri","43.323773","-3.015552",db)
        insertarLocalizacion ("Getxo","Mlle. de Las Arenas","43.328535","-3.015255",db)
        insertarLocalizacion ("Getxo","C. P.º Marqués de Arriluce e Ibarra","43.340554","-3.014086",db)
        insertarLocalizacion ("Sopelana","Solondota Kalea","43.383942","-3.001411",db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, NewVersion: Int) {
        db!!.execSQL("drop table if exists eveofe")
        db!!.execSQL("drop table if exists carta")
        db!!.execSQL("drop table if exists localizacion")
        db!!.execSQL("drop table if exists usuarios")
        db!!.execSQL("drop table if exists reserva")
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

    //Insercción de ofertas y eventos en la base de datos
    fun insertarEventoOfe(titulo:String,fecha:String,tipo: String, db:SQLiteDatabase?){
        val registrar2=ContentValues()
        registrar2.put("nombre",titulo)
        registrar2.put("fecha",fecha)
        registrar2.put("tipo",tipo)
        db?.insert("eveofe",null,registrar2)
    }

    fun insertarLocalizacion(municipio1:String,calle1:String,coordenada1:String,coordenada2:String, db:SQLiteDatabase?){
        val registrar2=ContentValues()
        registrar2.put("municipio",municipio1)
        registrar2.put("calle",calle1)
        registrar2.put("coordenada1",coordenada1)
        registrar2.put("coordenada2",coordenada2)
        db?.insert("localizacion",null,registrar2)
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

    //Funcion para recoger en una lista todos los  eventos o ofertas que hay en la carta
    fun Evento_ofe(tipo:String):List<eve_ofe>{
        val fila:MutableList<eve_ofe> = ArrayList()
        val db=this.readableDatabase
            val cursor:Cursor = db.rawQuery("select nombre from eveofe where tipo=? order by fecha", arrayOf(tipo))
            while (cursor.moveToNext()){
                val todo= eve_ofe(cursor.getString(0))
                fila.add(todo)

        }

        return fila
    }
    fun Main():List<eve_ofe>{
        val fila:MutableList<eve_ofe> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select nombre from eveofe order by fecha", arrayOf())
        while (cursor.moveToNext()){
            val todo= eve_ofe(cursor.getString(0))
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



    fun Municipio():MutableList<String>{
        val fila:MutableList<String> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select municipio from localizacion group by municipio", null)
        while (cursor.moveToNext()){
            val todo = cursor.getString(0)
            fila.add(todo)
        }
        return fila
    }
    fun Calle(municip:String):MutableList<String>{
        val fila:MutableList<String> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select calle from localizacion where municipio=? ", arrayOf(municip))
        while (cursor.moveToNext()){
            val todo= cursor.getString(0)
            fila.add(todo)
        }
        return fila
    }
    fun Coordenadas(calle:String):MutableList<coordenadas>{
        val fila:MutableList<coordenadas> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select coordenada1,coordenada2 from localizacion where calle=? ", arrayOf(calle))
        while (cursor.moveToNext()){
            val todo= coordenadas(cursor.getDouble(0),cursor.getDouble(1))
            fila.add(todo)
        }
        return fila
    }

    fun Coordenadas2():MutableList<coordenadas2>{
        val fila:MutableList<coordenadas2> = ArrayList()
        val db=this.readableDatabase
        val cursor:Cursor = db.rawQuery("select calle, coordenada1, coordenada2 from localizacion", null)
        while (cursor.moveToNext()){
            val todo= coordenadas2(cursor.getString(0), cursor.getString(1),cursor.getString(2))
            fila.add(todo)
        }
        return fila
    }
    fun actualizar(usuarioold:String,usuarionew:String,contraseña:String){
        val db=this.writableDatabase
        val fila=ContentValues()
        fila.put("user",usuarionew)
        fila.put("contrasena",contraseña)
        db.update("usuarios",fila,"user=?", arrayOf(usuarioold))
    }
    fun insertReserva(fecha: String,hora:String,comensales:String,municipio:String,calle:String){
        val db=this.writableDatabase
        val fila=ContentValues()
        fila.put("fecha",fecha)
        fila.put("hora",hora)
        fila.put("comensales",comensales)
        fila.put("municipio",municipio)
        fila.put("calle",calle)
        db?.insert("reserva",null,fila)
    }
}