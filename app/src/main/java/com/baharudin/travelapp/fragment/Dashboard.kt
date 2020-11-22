package com.baharudin.travelapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baharudin.travelapp.R
import com.baharudin.travelapp.TujuanActivity
import com.baharudin.travelapp.utils.Preference
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_dashboard.*


class Dashboard : Fragment() {


    private lateinit var preference : Preference
    lateinit var dataRef : DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataRef = FirebaseDatabase.getInstance().getReference("Users")
        preference = Preference(requireActivity().applicationContext)

        tv_nama.text = preference.getData("username")

        if (preference.getData("foto").equals("")){
            iv_foto.setImageResource(R.drawable.ic_person)
        }else{
            Glide.with(this)
                .load(preference.getData("foto"))
                .apply(RequestOptions.circleCropTransform())
                .into(iv_foto)
        }

        iv_bus.setOnClickListener {
            startActivity(Intent(activity,TujuanActivity::class.java))
        }
    }


}