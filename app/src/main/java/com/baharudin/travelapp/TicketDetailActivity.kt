package com.baharudin.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.travelapp.databinding.ActivityTicketDetailBinding
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*

class TicketDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTicketDetailBinding
    private lateinit var preference : Preference
    private lateinit var databaseReference : DatabaseReference
    private lateinit var dataRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)

        val bundle = intent.extras
        val lokasiTiket = bundle?.getString("data_tiket")

        dataRef = FirebaseDatabase.getInstance().getReference("MyTicket")
            .child(lokasiTiket!!)
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            .child(preference.getData("username")!!)

        getDataUser()
        getDataTiket()

    }
    private fun getDataTiket() {
        dataRef.child(preference.getData("username")!!).get().addOnSuccessListener {
            if (it.exists()) {
                val tujuanAwal = it.child("tujuanAwal").value
                val tujuanAkhir = it.child("tujuanAkhir").value
                val tempatAwal = it.child("tempatAwal").value
                val tempatAkhir = it.child("tempatAkhir").value
                val tanggal = it.child("tanggal").value
                val total = it.child("total").value

                binding.tvTujuanAwal.text = tujuanAwal.toString()
                binding.tvTujuanAkhir.text = tempatAwal.toString()
                binding.tvTanggal.text = tanggal.toString()
                binding.tvDarimana.text = tujuanAkhir.toString()
                binding.tvKemana.text = tempatAkhir.toString()
                binding.tvTotal.text = total.toString()
            }
        }
    }
    private fun getDataUser() {
      databaseReference.get().addOnSuccessListener {
          if (it.exists()){
              val namaPelanggan = it.child("username").value
              val noHpPelanggan = it.child("nohp").value
              val emailPelanggan = it.child("email").value
              Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
              binding.tvNamaPelanggan.text = namaPelanggan.toString()
              binding.tvHpPelanggan.text = noHpPelanggan.toString()
              binding.tvEmailPelanggan.text = emailPelanggan.toString()
          }else{
              Toast.makeText(this, "pelanggan gagal diambil", Toast.LENGTH_SHORT).show()
          }
      }.addOnFailureListener {
          Toast.makeText(this, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
      }
    }
}