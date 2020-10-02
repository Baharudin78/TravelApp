package com.baharudin.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.BusAdaptor
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_bus.*

class BusActivity : AppCompatActivity() {

    lateinit var mDatabaseRef : DatabaseReference
    lateinit var preference : Preference
    var dataList = ArrayList<Bus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)

        preference = Preference(this)
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Travels")
        tv_tujuan_awal.setText(preference.getData("tujuanAwal"))
        tv_tujuan_akhir.setText(preference.getData("tempatAwal"))
        tv_tanggal.setText(preference.getData("tanggal"))

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
                rv_daftar_mobil.adapter = BusAdaptor(dataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BusActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}