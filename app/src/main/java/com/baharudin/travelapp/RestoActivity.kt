package com.baharudin.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharudin.travelapp.databinding.ActivityRestoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RestoActivity : AppCompatActivity() {
    private lateinit var dataRef : DatabaseReference
    private lateinit var binding : ActivityRestoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef = FirebaseDatabase.getInstance().getReference("RestArea")

    }
}