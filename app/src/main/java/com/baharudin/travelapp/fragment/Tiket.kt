package com.baharudin.travelapp.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.FragmentTiketBinding


class Tiket : Fragment(R.layout.fragment_tiket) {
    private var _binding : FragmentTiketBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTiketBinding.bind(view)
    }

}