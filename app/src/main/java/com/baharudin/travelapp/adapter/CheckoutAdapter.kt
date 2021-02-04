package com.baharudin.travelapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.travelapp.R
import com.baharudin.travelapp.model.Bus
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var list : List<Bus?>,private var listener : (Bus?) -> Unit) : RecyclerView.Adapter<CheckoutAdapter.MyHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflate = inflater.inflate(R.layout.item_checkout,parent,false)
        return MyHolder(inflate)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        list[position]?.let { holder.bindItem(it,listener,contextAdapter) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyHolder(view : View): RecyclerView.ViewHolder(view){
        private var tvKursi : TextView = view.findViewById(R.id.tv_kursi)
        private var tvHarga : TextView = view.findViewById(R.id.tv_harga)

        fun bindItem(list : Bus?, listener: (Bus?) -> Unit,context: Context){
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
}