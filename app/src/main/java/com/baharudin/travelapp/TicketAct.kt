package com.baharudin.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharudin.travelapp.databinding.ActivityTicketBinding
import com.baharudin.travelapp.utils.Preference

class TicketAct : AppCompatActivity() {
    lateinit var binding : ActivityTicketBinding

    lateinit var preference : Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)

    }
}