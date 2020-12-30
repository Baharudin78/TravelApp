package com.baharudin.travelapp.onboarding.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baharudin.travelapp.databinding.FragmentViewPagerBinding
import com.baharudin.travelapp.onboarding.fragmant.FIrstScreen
import com.baharudin.travelapp.onboarding.fragmant.SecondScreen
import com.baharudin.travelapp.onboarding.fragmant.ThirdScreen

class ViewPagerFragment : Fragment() {
    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val fragmentList = arrayListOf<Fragment>(
            FIrstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPagerr.adapter = adapter

        return view
    }

}