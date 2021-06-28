package com.baharudin.travelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.RestoAdapter
import com.baharudin.travelapp.databinding.ActivityRestoBinding
import com.baharudin.travelapp.model.Resto
import com.google.firebase.database.*

class RestoActivity : AppCompatActivity() {
    private lateinit var dataRef : DatabaseReference
    private lateinit var binding : ActivityRestoBinding
    private var dataList = ArrayList<Resto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef = FirebaseDatabase.getInstance().getReference("RestArea")

        setupRecycleview()
        showProgressBar()
        getDataResto()


    }
    private fun setupRecycleview() {
        binding.apply {
            rvDaftarResto.layoutManager = LinearLayoutManager(this@RestoActivity)
        }
    }
    private fun getDataResto() {
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getResto in snapshot.children) {
                    val resto = getResto.getValue(Resto::class.java)
                    dataList.add(resto!!)
                }
                hideProgressBar()
                binding.rvDaftarResto.adapter = RestoAdapter(dataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RestoActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun showProgressBar() {
        binding.progressBar2.visibility = View.VISIBLE
    }
    private fun hideProgressBar() {
        binding.progressBar2.visibility = View.INVISIBLE
    }
}