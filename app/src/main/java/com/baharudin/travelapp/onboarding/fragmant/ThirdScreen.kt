package com.baharudin.travelapp.onboarding.fragmant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.baharudin.travelapp.LoginActivity
import com.baharudin.travelapp.R
import kotlinx.android.synthetic.main.fragment_third_screen.*
import kotlinx.android.synthetic.main.fragment_third_screen.view.*

class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third_screen, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPagerr)
        view.tv_next3.setOnClickListener {
            viewPager?.currentItem  = 3
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_next3.setOnClickListener {
            startActivity(Intent(activity,LoginActivity::class.java))
        }
    }

}