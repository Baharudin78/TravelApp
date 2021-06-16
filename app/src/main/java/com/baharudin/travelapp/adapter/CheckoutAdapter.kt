package com.baharudin.travelapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.databinding.ItemCheckoutBinding
import com.baharudin.travelapp.model.Bus
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var list : List<Bus?>,private var listener : (Bus?) -> Unit) : RecyclerView.Adapter<CheckoutAdapter.MyHolder>() {

    private lateinit var contextAdapter : Context

    inner class MyHolder(val binding : ItemCheckoutBinding): RecyclerView.ViewHolder(binding.root){
        private var tvKursi : TextView = binding.tvKursi
        private var tvHarga : TextView = binding.tvHarga

        fun bindItem(list: Bus?, listener: (Bus?) -> Unit){
            tvKursi.text = list?.kursi

            //function membuat format mata uang
            val localId = Locale("id","ID")
            val harga = NumberFormat.getCurrencyInstance(localId)
            tvHarga.setText(harga.format(list?.harga!!.toDouble()))
            //methot untuk menghilangkan icon kursi di recycleview
            if (list.kursi!!.startsWith("Total")){
                tvKursi.setText(list.kursi)
                tvKursi.setCompoundDrawables(null,null,null,null)
            }else{
                tvKursi.setText("Kursi no. " + list.kursi)
            }
            itemView.setOnClickListener {
                listener(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = ItemCheckoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        contextAdapter = parent.context
        return MyHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        list[position]?.let { holder.bindItem(it, listener) }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}