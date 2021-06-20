package com.baharudin.travelapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.travelapp.R
import com.baharudin.travelapp.TicketDetailActivity
import com.baharudin.travelapp.databinding.FragmentTiketBinding
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Tiket : Fragment(R.layout.fragment_tiket) {

    private lateinit var dataRef : DatabaseReference
    lateinit var preference: Preference
    private var _binding : FragmentTiketBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTiketBinding.bind(view)

        preference = Preference(requireContext().applicationContext)
        dataRef = FirebaseDatabase.getInstance().getReference("MyTicket")

        binding.ivBandung.setOnClickListener {
            val goBandung = Intent(requireContext(),TicketDetailActivity::class.java)
            goBandung.putExtra("data_tiket","Bandung")
            startActivity(goBandung)
        }
        binding.ivBatang.setOnClickListener {
            val tiketBatang = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketBatang.putExtra("data_tiket","Batang")
            startActivity(tiketBatang)
        }
        binding.ivBekasi.setOnClickListener {
            val tiketBekasi = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketBekasi.putExtra("data_tiket","Bekasi")
            startActivity(tiketBekasi)
        }
        binding.ivCirebon.setOnClickListener {
            val tiketCirebon = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketCirebon.putExtra("data_tiket", "Cirebon")
            startActivity(tiketCirebon)
        }
        binding.ivJakarta.setOnClickListener {
            val tiketJakarta = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketJakarta.putExtra("data_tiket", "Jakarta")
            startActivity(tiketJakarta)
        }
        binding.ivJogja.setOnClickListener {
            val tiketJogja = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketJogja.putExtra("data_tiket","Jogja")
            startActivity(tiketJogja)
        }
        binding.ivMalang.setOnClickListener {
            val tiketMalang = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketMalang.putExtra("data_tiket", "Jakarta")
            startActivity(tiketMalang)
        }
        binding.ivPekalongan.setOnClickListener {
            val tiketPekalongan = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketPekalongan.putExtra("data_tiket", "Pekalongan")
            startActivity(tiketPekalongan)
        }
        binding.ivSemarang.setOnClickListener {
            val tiketSemarang = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketSemarang.putExtra("data_tiket","Semarang")
            startActivity(tiketSemarang)
        }
        binding.ivSukabumi.setOnClickListener {
            val tiketSukabumi = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketSukabumi.putExtra("data_tiket","Sukabumi")
            startActivity(tiketSukabumi)
        }
        binding.ivSurabaya.setOnClickListener {
            val tiketSurabaya = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketSurabaya.putExtra("data_tiket","Surabaya")
            startActivity(tiketSurabaya)
        }
        binding.ivTangerang.setOnClickListener {
            val tiketTangerang = Intent(requireContext(), TicketDetailActivity::class.java)
            tiketTangerang.putExtra("data_tiket","Tangerang")
            startActivity(tiketTangerang)
        }

    }

}