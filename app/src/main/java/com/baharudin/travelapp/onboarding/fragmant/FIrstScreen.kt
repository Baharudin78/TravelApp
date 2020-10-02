package com.baharudin.travelapp.onboarding.fragmant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.baharudin.travelapp.R
import kotlinx.android.synthetic.main.fragment_f_irst_screen.view.*


class FIrstScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_f_irst_screen, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPagerr)

        view.tv_next1.setOnClickListener {
            viewPager?.currentItem = 1

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


}