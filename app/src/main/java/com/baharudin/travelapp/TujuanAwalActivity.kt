package com.baharudin.travelapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.baharudin.travelapp.adapter.DestinationAdapter
import com.baharudin.travelapp.model.Destination
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_tujuan_awal.*

class TujuanAwalActivity : AppCompatActivity() {

    private var dataList = ArrayList<Destination>()
    lateinit var dataRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tujuan_awal)

        dataRef = FirebaseDatabase.getInstance().getReference("Address")
        getData()
        rv_alamat.layoutManager = GridLayoutManager(this,2)

    }
    private fun getData(){
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for (getSnapshot in snapshot.children){
                    val tempat = getSnapshot.getValue(Destination::class.java)
                    dataList.add(tempat!!)
                }
                rv_alamat.adapter = DestinationAdapter(dataList){
                    val intent = Intent()
                    intent.putExtra("key",it.nama.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TujuanAwalActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}