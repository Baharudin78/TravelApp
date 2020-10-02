package com.baharudin.travelapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.R
import com.baharudin.travelapp.model.Bus
import com.bumptech.glide.Glide

class BusAdaptor (private var data : List<Bus>) : RecyclerView.Adapter<BusAdaptor.MyViewHolder>(){

    lateinit var contextAdapter : Context

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private var ivFoto : ImageView = view.findViewById(R.id.iv_foto_travel)
        private var tvNama : TextView = view.findViewById(R.id.tv_nama_travel)
        private var tvFasilitas : TextView = view.findViewById(R.id.tv_fasilitas)

        fun bindItem( data : Bus,context : Context){
            tvNama.text = data.nama
            tvFasilitas.text = data.falitas
            Glide.with(context)
                .load(data.foto)
                .into(ivFoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflater = layoutInflater.inflate(R.layout.item_travel_list,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],contextAdapter)
    }

    override fun getItemCount(): Int = data.size

}