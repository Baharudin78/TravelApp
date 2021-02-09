package com.baharudin.travelapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivitySuccesBinding

class SuccesAct : AppCompatActivity() {

    lateinit var binding : ActivitySuccesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        startActivity(Intent(this,HomeActivity::class.java))
    }

}