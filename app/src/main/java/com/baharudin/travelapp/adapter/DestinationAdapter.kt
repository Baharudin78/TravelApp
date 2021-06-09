package com.baharudin.travelapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.databinding.ItemDestinationBinding
import com.baharudin.travelapp.model.Destination

class DestinationAdapter (private var data : List<Destination>, private var listener : (Destination) -> Unit ) : RecyclerView.Adapter<DestinationAdapter.MyViewHolder>() {

    class MyViewHolder(val binding : ItemDestinationBinding ) : RecyclerView.ViewHolder(binding.root){
        private var tvTempat : TextView = binding.tvTempat

        fun bindItem (data : Destination , listener: (Destination) -> Unit) {
            tvTempat.text = data.nama

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = ItemDestinationBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,false
        )
        return MyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],listener)
    }

    override fun getItemCount(): Int = data.size
}