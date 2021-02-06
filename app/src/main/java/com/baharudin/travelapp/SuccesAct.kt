package com.baharudin.travelapp

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivitySuccesBinding

class SuccesAct : AppCompatActivity() {

    lateinit var binding : ActivitySuccesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Kabur dari Laman ini ?")
        alertDialog.setMessage("Anda akan kembali ke halam dashboard")


    }
}