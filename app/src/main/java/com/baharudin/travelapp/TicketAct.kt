package com.baharudin.travelapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivityTicketBinding
import com.baharudin.travelapp.utils.Preference


class TicketAct : AppCompatActivity() {
    lateinit var binding : ActivityTicketBinding

    lateinit var preference : Preference
    private val nomorTelepon : String = "+6285230155923"
    private val pesanText : String = "Mau konfirmasi pembayaran tiket travel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)

        binding.tvTujuanAwal.text = preference.getData("tujuanAwal")
        binding.tvDarimana.text = preference.getData("tujuanAkhir")
        binding.tvTujuanAkhir.text = preference.getData("tempatAwal")
        binding.tvKemana.text = preference.getData("tempatAkhir")
        binding.tvTanggal.text = preference.getData("tanggal")
        binding.tvTotal.text = preference.getData("total")

        binding.btUploadBukti.setOnClickListener {
            val installed: Boolean = isAppInstalled("com.whatsapp")
            if (installed) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                    Uri.parse("http://api.whatsapp.com/send?phone=" + nomorTelepon.toString() + "&text=" + pesanText)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Whatsapp is not installed!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        binding.back.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finishAffinity()
        }

    }

    private fun isAppInstalled(s: String): Boolean {
        val packageManager = packageManager
        var is_installed: Boolean

        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES)
            is_installed = true
        } catch (e: PackageManager.NameNotFoundException) {
            is_installed = false
            e.printStackTrace()
        }
        return is_installed
    }

}