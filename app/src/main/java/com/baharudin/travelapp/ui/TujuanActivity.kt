package com.baharudin.travelapp.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivityTujuanBinding
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class TujuanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTujuanBinding

    private lateinit var tujuanAwal: String
    private lateinit var tempatAwal: String
    private lateinit var tujuanAkhir: String
    private lateinit var tempatAkhir: String
    private lateinit var tanggalBerangkat: String
    private lateinit var iUsername: String
    private lateinit var iKemana : String

    private lateinit var dataRef : DatabaseReference
    private lateinit var firebaseInstance : FirebaseDatabase
    private lateinit var mDataRef : DatabaseReference
    lateinit var preference : Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTujuanBinding.inflate(layoutInflater)
        setContentView(binding.root)



        iUsername = binding.tvNamaku.text.toString()
        tujuanAwal = binding.tvDarimana.text.toString()
        tempatAwal = binding.tvRincian1.text.toString()
        tujuanAkhir = binding.tvKemana.text.toString()
        tempatAkhir = binding.tvRincian2.text.toString()
        tanggalBerangkat = binding.tvTanggal.text.toString()

        preference = Preference(this)
        iKemana = binding.tvKemana.text.toString()
//        iUsername = binding.tvNamaku.text.toString()

        firebaseInstance = FirebaseDatabase.getInstance()
        dataRef = FirebaseDatabase.getInstance().getReference()
        mDataRef = firebaseInstance.getReference().child("MyTicket").child(tujuanAkhir)

        binding.tvNamaku.setText(preference.getData("username"))
        binding.tvDarimana.setOnClickListener {
            
            val intent = Intent(this , TujuanAwalActivity::class.java)
            startActivityForResult(intent, 10)
        }
        binding.tvKemana.setOnClickListener {
            val intent2 = Intent(this, DestinasiActivity::class.java)
            startActivityForResult(intent2,13)
        }

        binding.btLanjutkan.setOnClickListener {

            iUsername = binding.tvNamaku.text.toString()
            tujuanAwal = binding.tvDarimana.text.toString()
            tempatAwal = binding.tvRincian1.text.toString()
            tujuanAkhir = binding.tvKemana.text.toString()
            tempatAkhir = binding.tvRincian2.text.toString()
            tanggalBerangkat = binding.tvTanggal.text.toString()

            if(tujuanAwal.isEmpty()){
                binding.tvDarimana.error = "mohon pilih tujuan anda"
                binding.tvDarimana.requestFocus()
            }else if (tempatAwal.isEmpty()){
                binding.tvRincian1.error = "dimana jemputnya"
                binding.tvRincian1.requestFocus()
            }else if (tujuanAkhir.isEmpty()){
                binding.tvKemana.error ="mohon isi tujuannya"
                binding.tvKemana.requestFocus()
            }else if (tempatAkhir.isEmpty()){
                binding.tvRincian2.error = "dimana turunnya"
                binding.tvRincian2.requestFocus()
            }else if(tanggalBerangkat.isEmpty()){
                binding.tvTanggal.error = "isi tanggal keberangkatan"
                binding.tvTanggal.requestFocus()
            }else{
                binding.btLanjutkan.text ="Tunggu"
                binding.btLanjutkan.isEnabled = false
                savePlace(iUsername,tujuanAwal,tempatAwal,tujuanAkhir,tempatAkhir,tanggalBerangkat)
            }
        }
        binding.tvTanggal.setOnClickListener {
            val newCalendar: Calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                    binding.tvTanggal.setText(dateFormatter.format(newDate.getTime()))
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10){
            if (resultCode == Activity.RESULT_OK){
                binding.tvDarimana.text = data?.getStringExtra("key")
            }
        }
        if (requestCode == 13){
            if (resultCode == Activity.RESULT_OK){
                binding.tvKemana.text = data?.getStringExtra("key1")
            }
        }
    }

    private fun savePlace(iUsername: String,tujuanAwal : String, tujuanAkhir : String, tempatAwal : String, tempatAkhir : String, tanggalBerangkat : String){

        val tiket = Ticket()
        tiket.username = iUsername
        tiket.tujuanAwal = tujuanAwal
        tiket.tempatAwal = tempatAwal
        tiket.tujuanAkhir = tujuanAkhir
        tiket.tempatAkhir = tempatAkhir
        tiket.tanggal = tanggalBerangkat
        addData(iUsername,tiket)
    }

    private fun addData(iUsername: String ,tiket : Ticket){
        mDataRef.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mDataRef.child(tujuanAkhir).child(iUsername).setValue(tiket)

                preference.setData("username", tiket.username).toString()
                preference.setData("tujuanAwal", tiket.tujuanAwal.toString())
                preference.setData("tempatAwal", tiket.tempatAwal.toString())
                preference.setData("tujuanAkhir", tiket.tujuanAkhir.toString())
                preference.setData("tempatAkhir", tiket.tempatAkhir.toString())
                preference.setData("tanggal", tiket.tanggal.toString())
                preference.setData("total","")


                val intent = Intent(this@TujuanActivity, BusActivity::class.java)
                intent.putExtra("data", tiket)
                startActivity(intent)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TujuanActivity , "database error", Toast.LENGTH_SHORT).show()
            }

        })

    }
}