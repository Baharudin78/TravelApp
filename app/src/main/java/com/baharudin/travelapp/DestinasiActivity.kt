package com.baharudin.travelapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharudin.travelapp.model.Destination
import kotlinx.android.synthetic.main.activity_destinasi.*

class DestinasiActivity : AppCompatActivity() {
    private var dataList = ArrayList<Destination>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinasi)

        tv_jakarta.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Jakarta")
            hasil.putExtra("key3","450000")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_bandung.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Bandung")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_bekasi.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Bekasi")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_sukabumi.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Sukabumi")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_tangerang.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Tangerang")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_batang.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Batang")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_cirebon.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Cirebon")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_jogja.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Jogja")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_malang.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Malang")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_surabaya.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Surabaya")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_pekalongan.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Pekalongan")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }

        tv_semarang.setOnClickListener {
            val hasil = Intent()
            hasil.putExtra("key1","Semarang")
            setResult(Activity.RESULT_OK,hasil)
            finish()
        }
    }
}