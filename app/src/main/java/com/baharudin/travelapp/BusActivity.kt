package com.baharudin.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.BusAdaptor
import com.baharudin.travelapp.databinding.ActivityBusBinding
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*

class BusActivity : AppCompatActivity() {

    lateinit var mDatabaseRef : DatabaseReference
    lateinit var preference : Preference
    var dataList = ArrayList<Bus>()
    lateinit var binding : ActivityBusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        iDestinstion = tv_tujuan_akhir.text.toString()
        val data = intent.getParcelableExtra<Ticket>("data")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Destination")
            .child(data?.tempatAwal.toString())
            .child("Travels")
        preference = Preference(this)

        binding.tvTujuanAwal.text = data?.tujuanAwal
        binding.tvTujuanAkhir.text = data?.tempatAwal
        binding.tvTanggal.text = data?.tanggal

        binding.ivBack.setOnClickListener {

        }


        binding.rvDaftarMobil.layoutManager = LinearLayoutManager(this)
        getData()
    }
    private fun getData (){
        mDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getTravel in snapshot.children){
                    val travel = getTravel.getValue(Bus::class.java)
                    dataList.add(travel!!)
                }
                binding.rvDaftarMobil.adapter = BusAdaptor(dataList){
                    val intent = Intent(this@BusActivity,KursiActivity::class.java)
                    intent.putExtra("data1",it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BusActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}