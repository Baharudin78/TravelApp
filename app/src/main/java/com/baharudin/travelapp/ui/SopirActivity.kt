package com.baharudin.travelapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.SopirAdapter
import com.baharudin.travelapp.databinding.ActivitySopirBinding
import com.baharudin.travelapp.model.Sopir
import com.google.firebase.database.*

class SopirActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySopirBinding
    private lateinit var dataRef : DatabaseReference
    private var dataList = ArrayList<Sopir>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySopirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef = FirebaseDatabase.getInstance().getReference("Sopir")
        setupRecycleView()
        showProgressbar()
        getDataSopir()

    }

    private fun getDataSopir() {
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getSupir in snapshot.children) {
                    val sopir = getSupir.getValue(Sopir::class.java)
                    dataList.add(sopir!!)
                }
                hideProgresbar()
                binding.rvSopir.adapter = SopirAdapter(dataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SopirActivity, error.message , Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun showProgressbar() {
        binding.progressBar4.visibility = View.VISIBLE
    }

    private fun setupRecycleView() {
        binding.apply {
            rvSopir.layoutManager = LinearLayoutManager(this@SopirActivity)
        }
    }
    private fun hideProgresbar() {
        binding.progressBar4.visibility = View.INVISIBLE
    }
}