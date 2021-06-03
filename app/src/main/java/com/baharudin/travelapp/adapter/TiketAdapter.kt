package com.baharudin.travelapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.databinding.ItemTiketBinding
import com.baharudin.travelapp.model.Ticket

class TiketAdapter(private var data : List<Ticket>) : RecyclerView.Adapter<TiketAdapter.TiketHolder>() {

    inner class TiketHolder(binding : ItemTiketBinding) : RecyclerView.ViewHolder(binding.root){
        private var etTujuanAwal : TextView = binding.tvTujuanawal
        private var etTujuanAkhir: TextView = binding.tvTujuanakhir
        private var etTanggal : TextView = binding.tvTanggal

        fun bindItem(data : Ticket){
            etTujuanAwal.text = data.tujuanAwal
            etTujuanAkhir.text = data.tempatAwal
            etTanggal.text = data.tanggal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiketHolder {
        val inflater = ItemTiketBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TiketHolder(inflater)
    }

    override fun onBindViewHolder(holder: TiketHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int = data.size

}