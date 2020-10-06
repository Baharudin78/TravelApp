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
import kotlinx.android.synthetic.main.activity_destinasi.*

class DestinasiActivity : AppCompatActivity() {

    lateinit var dataRef : DatabaseReference
    private var dataList = ArrayList<Destination>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinasi)

        dataRef = FirebaseDatabase.getInstance().getReference("Destination")

        getData()
        rv_destination.layoutManager = GridLayoutManager(this, 2)

    }
    private fun getData(){
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for ( getSnapshot in snapshot.children){
                    val tempat = getSnapshot.getValue(Destination::class.java)
                    dataList.add(tempat!!)
                }
                rv_destination.adapter = DestinationAdapter(dataList){
                    val intent = Intent()
                    intent.putExtra("key1", it.nama.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DestinasiActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}