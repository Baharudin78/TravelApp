package com.baharudin.travelapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.baharudin.travelapp.adapter.DestinationAdapter
import com.baharudin.travelapp.databinding.ActivityDestinasiBinding
import com.baharudin.travelapp.model.Destination
import com.google.firebase.database.*

class DestinasiActivity : AppCompatActivity() {

    lateinit var dataRef : DatabaseReference
    private var dataList = ArrayList<Destination>()
    lateinit var binding : ActivityDestinasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef = FirebaseDatabase.getInstance().getReference("Destination")
        showProgressBar()
        getData()
        binding.rvDestination.layoutManager = GridLayoutManager(this, 2)

    }
    private fun getData(){
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for ( getSnapshot in snapshot.children){
                    val tempat = getSnapshot.getValue(Destination::class.java)
                    hideProgressBar()
                    dataList.add(tempat!!)
                }
                binding.rvDestination.adapter = DestinationAdapter(dataList){
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
    private fun showProgressBar() {
        binding.progressBar5.visibility = View.VISIBLE
    }
    private fun hideProgressBar() {
        binding.progressBar5.visibility = View.INVISIBLE
    }
}