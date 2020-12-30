package com.baharudin.travelapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.FragmentSettingBinding


class Setting : Fragment(R.layout.fragment_setting) {

    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingBinding.bind(view)

    }
}