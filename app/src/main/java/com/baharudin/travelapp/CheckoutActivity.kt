package com.baharudin.travelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.CheckoutAdapter
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.utils.Preference
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    lateinit var preference: Preference
    private var dataList = ArrayList<Bus?>()
    private var total : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preference = Preference(this)

        dataList = intent.getSerializableExtra("data") as ArrayList<Bus?>

        tv_tujuan_awal.text = preference.getData("tujuanAwal")
        tv_tujuan_akhir.text = preference.getData("tempatAwal")
        tv_tanggal.text = preference.getData("tanggal")

        for (a in dataList.indices){
            total += dataList[a]?.harga!!.toInt()
        }
        dataList.add(Bus("Total yang harus dibayar ",total.toString()))
        rv_detail.layoutManager = LinearLayoutManager(this)
        rv_detail.adapter = CheckoutAdapter(dataList){

        }

    }
}