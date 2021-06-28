package com.baharudin.travelapp.onboarding.fragmant

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.baharudin.travelapp.ui.LoginActivity
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment(R.layout.fragment_third_screen) {
    private var _binding : FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentThirdScreenBinding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPagerr)
        binding.tvNext3.setOnClickListener {
            viewPager?.currentItem = 3
        }
        binding.tvNext3.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java) )
        }
    }

}