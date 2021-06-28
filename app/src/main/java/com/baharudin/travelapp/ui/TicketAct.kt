package com.baharudin.travelapp.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivityTicketBinding
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*


class TicketAct : AppCompatActivity() {
    lateinit var binding : ActivityTicketBinding

    lateinit var preference : Preference
    private lateinit var dataRef : DatabaseReference
    private lateinit var tujuanAkhir : String
    private val nomorTelepon : String = "+6285230155923"
    private val pesanText : String = "Mau konfirmasi pembayaran tiket travel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        preference = Preference(this)

        binding.tvPenumpang.text = preference.getData("username")
        binding.tvTujuanAwal.text = preference.getData("tujuanAwal")
        binding.tvDarimana.text = preference.getData("tujuanAkhir")
        binding.tvTujuanAkhir.text = preference.getData("tempatAwal")
        binding.tvKemana.text = preference.getData("tempatAkhir")
        binding.tvTanggal.text = preference.getData("tanggal")
        binding.tvTotal.text = preference.getData("total")

        tujuanAkhir = binding.tvTujuanAkhir.text.toString()

        dataRef = FirebaseDatabase.getInstance().getReference("MyTicket")
            .child(tujuanAkhir)
            .child(preference.getData("username")!!)

        binding.rlBatalkan.setOnClickListener {
            val builder : AlertDialog.Builder =
                AlertDialog.Builder(this)
            builder.setTitle("Alert Dialog")
                .setMessage("Apakah anda ingin membatalkan order ini ?")
                .setPositiveButton("Oke"
                ) {dialog , which ->
                    deleteTiket()
                    finishAffinity()
                    val intent = Intent(this@TicketAct,HomeActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Batal"
                ) { dialog, which ->
                    Toast.makeText(
                        this,
                        "Batal diklik",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }



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
            startActivity(Intent(this, HomeActivity::class.java))
            finishAffinity()
        }

    }

    private fun deleteTiket() {
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataRef.ref.removeValue()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TicketAct, error.message, Toast.LENGTH_SHORT).show()
            }

        })
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