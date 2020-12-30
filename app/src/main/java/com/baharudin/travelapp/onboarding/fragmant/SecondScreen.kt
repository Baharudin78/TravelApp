package com.baharudin.travelapp.onboarding.fragmant

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.FragmentSecondScreenBinding


class SecondScreen : Fragment(R.layout.fragment_second_screen) {
    private var _binding : FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondScreenBinding.bind(view)
        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewPagerr)
        binding.tvNext2.setOnClickListener {
            viewpager?.currentItem = 2
        }

    }
}