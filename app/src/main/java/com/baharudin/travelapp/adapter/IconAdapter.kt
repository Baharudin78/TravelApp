package com.baharudin.travelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.R
import com.baharudin.travelapp.model.Icons

class IconAdapter (private var list : List<Icons>) : RecyclerView.Adapter<IconAdapter.MyHolder>(){
    inner class MyHolder(view : View) : RecyclerView.ViewHolder(view){
        val icPerson : ImageView = view.findViewById(R.id.ic_person)
        val icArraw : ImageView = view.findViewById(R.id.ic_arraw)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_identity,parent,false)
        return MyHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentIcon = list[position]
        holder.icPerson.setImageResource(currentIcon.person)
        holder.icArraw.setImageResource(currentIcon.arrow)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}