package com.baharudin.travelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivityTicketBinding
import com.baharudin.travelapp.utils.Preference

class TicketAct : AppCompatActivity() {
    lateinit var binding : ActivityTicketBinding

    lateinit var preference : Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)

        binding.tvTujuanAwal.text = preference.getData("tujuanAwal")
        binding.tvDarimana.text = preference.getData("tujuanAwal")
        binding.tvTujuanAkhir.text = preference.getData("tempatAwal")
        binding.tvKemana.text = preference.getData("tempatAwal")
        binding.tvTanggal.text = preference.getData("tanggal")
        binding.tvTanggalKeberangkatan.text = preference.getData("tanggal")
        binding.tvTotal.text = preference.getData("total")



    }

}