package com.baharudin.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.BusAdaptor
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_bus.*

class BusActivity : AppCompatActivity() {

//
//    lateinit var iDestinstion : String

    lateinit var mDatabaseRef : DatabaseReference
    lateinit var preference : Preference
    var dataList = ArrayList<Bus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)
//
//        iDestinstion = tv_tujuan_akhir.text.toString()
        val data = intent.getParcelableExtra<Ticket>("data")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Destination")
            .child(data?.tempatAwal.toString())
            .child("Travels")
        preference = Preference(this)

        tv_tujuan_awal.text = data?.tujuanAwal
        tv_tujuan_akhir.text = data?.tempatAwal
        tv_tanggal.text = data?.tanggal


        rv_daftar_mobil.layoutManager = LinearLayoutManager(this)
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
                rv_daftar_mobil.adapter = BusAdaptor(dataList){
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