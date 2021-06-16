package com.baharudin.travelapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivityTicketDetailBinding
import com.baharudin.travelapp.model.Ticket

class TicketDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTicketDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Ticket>("data_tiket")

        binding.tvTujuanAwal.text = data?.tujuanAwal
        binding.tvDarimana.text = data?.tujuanAkhir
        binding.tvTujuanAkhir.text = data?.tempatAwal
        binding.tvKemana.text = data?.tempatAkhir
        binding.tvTanggal.text = data?.tanggal
        binding.tvTotal.text = data?.total


    }

}