package com.baharudin.travelapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.baharudin.travelapp.adapter.DestinationAdapter
import com.baharudin.travelapp.databinding.ActivityTujuanAwalBinding
import com.baharudin.travelapp.model.Destination
import com.google.firebase.database.*

class TujuanAwalActivity : AppCompatActivity() {

    private var dataList = ArrayList<Destination>()
    lateinit var dataRef : DatabaseReference
    lateinit var binding : ActivityTujuanAwalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTujuanAwalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef = FirebaseDatabase.getInstance().getReference("Address")
        showProgressBar()
        getData()
        binding.rvAlamat.layoutManager = GridLayoutManager(this,2)

    }
    private fun getData(){
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for (getSnapshot in snapshot.children){
                    val tempat = getSnapshot.getValue(Destination::class.java)
                    hideProgressBar()
                    dataList.add(tempat!!)
                }
                binding.rvAlamat.adapter = DestinationAdapter(dataList){
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
    private fun showProgressBar() {
        binding.progressBar4.visibility = View.VISIBLE
    }
    private fun hideProgressBar() {
        binding.progressBar4.visibility = View.INVISIBLE
    }
}