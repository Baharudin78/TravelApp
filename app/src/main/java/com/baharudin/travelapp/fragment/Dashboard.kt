package com.baharudin.travelapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.baharudin.travelapp.R
import com.baharudin.travelapp.RestoActivity
import com.baharudin.travelapp.TujuanActivity
import com.baharudin.travelapp.databinding.FragmentDashboardBinding
import com.baharudin.travelapp.utils.Preference
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Dashboard : Fragment(R.layout.fragment_dashboard) {

    private lateinit var preference : Preference
    private lateinit var dataRef : DatabaseReference
    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        dataRef = FirebaseDatabase.getInstance().getReference("Users")
        preference = Preference(requireActivity().applicationContext)

        binding.tvNama.text = preference.getData("username")
        activity?.findViewById<BottomNavigationView>(R.id.nav_bottom)

        if (preference.getData("foto").equals("")) {
            binding.ivFoto.setImageResource(R.drawable.ic_person)
        } else {
            Glide.with(this)
                .load(preference.getData("foto"))
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivFoto)
        }
        binding.ivBus.setOnClickListener {
            val intent = Intent(requireContext(), TujuanActivity::class.java)
            startActivity(intent)
        }
        binding.ivFood.setOnClickListener {
            val intent = Intent(requireContext(), RestoActivity::class.java)
            startActivity(intent)
        }


    }
}