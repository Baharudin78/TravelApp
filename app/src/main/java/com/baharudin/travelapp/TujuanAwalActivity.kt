package com.baharudin.travelapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tujuan_awal.*

class TujuanAwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tujuan_awal)

        tv_jatisrono.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Jatisrono")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_jatiroto.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Jatiroto")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_sidoharjo.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Sidoharjo")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_ngadirojo.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Ngadirojo")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_slogohimo.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Slogohimo")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_girimarto.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Girimarto")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_bulukerto.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Bulukerto")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_jatipurno.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Jatipurno")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }
        tv_wonogiri.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Wonogiri")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_selogiri.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Selogiri")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_manyaran.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Manyaran")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }

        tv_purwantoro.setOnClickListener {
            val resulr = Intent()
            resulr.putExtra("key","Purwantoro")
            setResult(Activity.RESULT_OK,resulr)
            finish()
        }




    }
}