package com.baharudin.travelapp.onboarding.fragmant

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.baharudin.travelapp.LoginActivity
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.FragmentFIrstScreenBinding
import com.baharudin.travelapp.utils.Preference


class FIrstScreen : Fragment(R.layout.fragment_f_irst_screen) {

    lateinit var preference : Preference
    private var _binding : FragmentFIrstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFIrstScreenBinding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPagerr)
        binding.tvNext1.setOnClickListener {
            preference.setData("onboarding1","1")
            viewPager?.currentItem = 1
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        preference = Preference(requireActivity().applicationContext)

        if (preference.getData("onboarding1").equals("1")){
                startActivity(Intent(activity,LoginActivity::class.java))
            }
    }

}