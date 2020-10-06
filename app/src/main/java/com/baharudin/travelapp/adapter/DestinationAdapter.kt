package com.baharudin.travelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.R
import com.baharudin.travelapp.model.Destination

class DestinationAdapter (private var data : List<Destination>, private var listener : (Destination) -> Unit ) : RecyclerView.Adapter<DestinationAdapter.MyViewHolder>() {

    class MyViewHolder(view : View ) : RecyclerView.ViewHolder(view){
        private var tvTempat : TextView = view.findViewById(R.id.tv_tempat)

        fun bindItem (data : Destination , listener: (Destination) -> Unit) {
            tvTempat.text = data.nama

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.item_destination,parent,false)
        return MyViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position],listener)
    }

    override fun getItemCount(): Int = data.size
}