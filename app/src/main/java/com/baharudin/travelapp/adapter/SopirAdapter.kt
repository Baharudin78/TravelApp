package com.baharudin.travelapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.ItemSopirBinding
import com.baharudin.travelapp.model.Sopir
import com.bumptech.glide.Glide

class SopirAdapter(private var data : List<Sopir>)  : RecyclerView.Adapter<SopirAdapter.SopirHolder>()  {

    private lateinit var contexAdapter : Context
    inner class SopirHolder(val binding : ItemSopirBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvNamaSopir : TextView = binding.tvNamaSopir
        val tvHpSopir : TextView = binding.tvHpsopir
        private val ivFoto : ImageView = binding.ivSopir

        fun bindData(data : Sopir,context: Context) {
            tvNamaSopir.text = data.namaSopir
            tvHpSopir.text = data.nohp
            Glide.with(context)
                .load(data.foto)
                .error(R.drawable.ic_driver)
                .into(ivFoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SopirHolder {
        val inflater = ItemSopirBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,false
        )
        contexAdapter = parent.context
        return SopirHolder(inflater)
    }

    override fun onBindViewHolder(holder: SopirHolder, position: Int) {
        holder.bindData(data[position],contexAdapter)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}