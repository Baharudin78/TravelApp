package com.baharudin.travelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.CheckoutAdapter
import com.baharudin.travelapp.databinding.ActivityCheckoutBinding
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.utils.Preference

class CheckoutActivity : AppCompatActivity() {

    lateinit var preference: Preference
    private var dataList = ArrayList<Bus?>()
    lateinit var binding : ActivityCheckoutBinding
    private var total : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)

        dataList = intent.getSerializableExtra("data") as ArrayList<Bus?>

        binding.tvTujuanAwal.text = preference.getData("tujuanAwal")
        binding.tvTujuanAkhir.text = preference.getData("tempatAwal")
        binding.tvTanggal.text = preference.getData("tanggal")

        for (a in dataList.indices){
            total += dataList[a]?.harga!!.toInt()
        }
        dataList.add(Bus("Total yang harus dibayar ",total.toString()))
        binding.rvDetail.layoutManager = LinearLayoutManager(this)
        binding.rvDetail.adapter = CheckoutAdapter(dataList){

        }

    }
}