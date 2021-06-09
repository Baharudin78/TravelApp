package com.baharudin.travelapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.databinding.ItemTravelListBinding
import com.baharudin.travelapp.model.Bus
import com.bumptech.glide.Glide

class BusAdaptor (private var data : List<Bus>, private  var listener : (Bus) -> Unit) : RecyclerView.Adapter<BusAdaptor.MyViewHolder>(){

    private lateinit var contextAdapter : Context

    class MyViewHolder(val binding : ItemTravelListBinding) : RecyclerView.ViewHolder(binding.root){
        private var ivFoto : ImageView = binding.ivFotoTravel
        private var tvNama : TextView = binding.tvNamaTravel
        private var tvFasilitas : TextView = binding.tvFasilitas
        private var tvHarga : TextView = binding.tvHarga

        fun bindItem( data : Bus,context : Context,listener: (Bus) -> Unit){
            tvNama.text = data.travel
            tvFasilitas.text = data.fasilitas
            tvHarga.text = data.harga
            Glide.with(context)
                .load(data.foto)
                .into(ivFoto)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = ItemTravelListBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,false
        )
        contextAdapter = parent.context
        return MyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],contextAdapter,listener)
    }

    override fun getItemCount(): Int = data.size

}