package com.baharudin.travelapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.databinding.ItemListRestoBinding
import com.baharudin.travelapp.model.Resto
import com.bumptech.glide.Glide

class RestoAdapter(private val data : List<Resto>) : RecyclerView.Adapter<RestoAdapter.RestoHolder>() {
    private lateinit var contextAdapter : Context
    inner class RestoHolder(val binding : ItemListRestoBinding) : RecyclerView.ViewHolder(binding.root){

        private val ivFotoResto : ImageView = binding.ivFotoResto
        private val tvNamaResto : TextView = binding.tvNamaResto
        private val tvNoTelepon : TextView = binding.tvHpResto
        private val tvAlamatResto : TextView = binding.tvAlamatResto

        fun bindItem(data: Resto, context : Context ) {
            tvNamaResto.text = data.nama
            tvNoTelepon.text = data.telepon
            tvAlamatResto.text = data.letak
            Glide.with(context)
                .load(data.foto)
                .into(ivFotoResto)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoHolder {
        val inflater = ItemListRestoBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,false
        )
        contextAdapter = parent.context
        return RestoHolder(inflater)
    }

    override fun onBindViewHolder(holder: RestoHolder, position: Int) {
        holder.bindItem(data[position],contextAdapter)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}