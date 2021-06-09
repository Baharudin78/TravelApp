package com.baharudin.travelapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivityTicketDetailBinding
import com.baharudin.travelapp.model.Ticket

class TicketDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTicketDetailBinding
    private val nomorTelepon : String = "+6285230155923"
    private val pesanText : String = "Mau konfirmasi pembayaran tiket travel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Ticket>("data_tiket")

        binding.tvTujuanAwal.text = data?.tujuanAwal
        binding.tvDarimana.text = data?.tujuanAkhir
        binding.tvTujuanAkhir.text = data?.tempatAwal
        binding.tvKemana.text = data?.tempatAkhir
        binding.tvTanggal.text = data?.tanggal
        binding.tvTotal.text = data?.total

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