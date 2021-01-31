package com.baharudin.travelapp.ui

import android.app.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.baharudin.travelapp.*
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.FragmentTujuanBinding
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class TujuanFragment : Fragment(R.layout.fragment_tujuan) {

    private var _binding : FragmentTujuanBinding? = null
    private val binding get() = _binding!!

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentTujuanBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        preference = Preference(requireContext())
        iKemana = binding.tvKemana.text.toString()
        iUsername = binding.tvNamaku.text.toString()
        firebaseInstance = FirebaseDatabase.getInstance()
        dataRef = FirebaseDatabase.getInstance().getReference()
        mDataRef = firebaseInstance.getReference().child("MyTicket").child(preference.getData("username")!!)


        binding.tvNamaku.setText(preference.getData("username"))
        binding.tvDarimana.setOnClickListener {

            val intent = Intent(requireContext(),TujuanAwalActivity::class.java)
            startActivityForResult(intent, 10)
        }
        binding.tvKemana.setOnClickListener {
            val intent2 = Intent(requireContext(),DestinasiActivity::class.java)
            startActivityForResult(intent2,13)
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_tujuanFragment_to_dashboard)
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

                savePlace(iUsername,tujuanAwal,tempatAwal,tujuanAkhir,tempatAkhir,tanggalBerangkat)
            }
        }

        binding.tvTanggal.setOnClickListener {
            val newCalendar: Calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                requireContext(),
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
        addData(tempatAwal,tiket)
    }

    private fun addData(tempatAwal: String, tiket : Ticket){
        mDataRef.child(tempatAwal).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mDataRef.child(tempatAwal).setValue(tiket)

                preference.setData("username", tiket.username).toString()
                preference.setData("tujuanAwal", tiket.tujuanAwal.toString())
                preference.setData("tempatAwal", tiket.tempatAwal.toString())
                preference.setData("tujuanAkhir", tiket.tujuanAkhir.toString())
                preference.setData("tempatAkhir", tiket.tempatAkhir.toString())
                preference.setData("tanggal", tiket.tanggal.toString())


                val intent = Intent(requireContext(), BusActivity::class.java)
                intent.putExtra("data", tiket)
                startActivity(intent)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "database error", Toast.LENGTH_SHORT).show()
            }

        })

    }

}