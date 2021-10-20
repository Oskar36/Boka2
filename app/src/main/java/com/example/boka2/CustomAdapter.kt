package com.example.boka2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.p_registro.view.*

data class CustomAdapter(val lista: IntArray) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.card_view_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id =lista[position]
        holder.imageView.setImageResource(id)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
    class  ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val imageView= ItemView.imageView
    }
}