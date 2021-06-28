package com.baharudin.travelapp.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.baharudin.travelapp.ui.LoginActivity
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.FragmentSettingBinding
import com.baharudin.travelapp.utils.Preference
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class Setting : Fragment(R.layout.fragment_setting) {

    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private lateinit var preference : Preference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingBinding.bind(view)

        preference = Preference(requireContext())

        showPhoto()
        showIdentity()

        binding.ivLogout.setOnClickListener {
            logOut()
        }

    }
    private fun showPhoto() {
        Glide.with(this)
            .load(preference.getData("foto"))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivFoto)
    }
    private fun showIdentity() {
        binding.tvNama.text = preference.getData("username")
        binding.tvEmail.text = preference.getData("email")
        binding.tvNohp.text = preference.getData("nohp")
    }
    private fun logOut() {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("Alert Dialog")
                .setMessage("Apakah anda ingin Logout")
                .setPositiveButton("Oke"
                ) { dialog, which ->
                    preference.clear()
                    Toast.makeText(
                        requireContext(),
                        "Tombol oke di klik",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    activity?.finish()
                }
                .setNegativeButton("Batal"
                ) { dialog, which ->
                    Toast.makeText(
                        context,
                        "Batal diklik",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        val dialog: AlertDialog = builder.create()
            dialog.show()

    }
}