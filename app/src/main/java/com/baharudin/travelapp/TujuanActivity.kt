package com.baharudin.travelapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_tujuan.*
import java.text.SimpleDateFormat
import java.util.*

class TujuanActivity : AppCompatActivity() {

    private lateinit var tujuanAwal: String
    private lateinit var tempatAwal: String
    private lateinit var tujuanAkhir: String
    private lateinit var tempatAkhir: String
    private lateinit var tanggalBerangkat: String
    private lateinit var iUsername: String
    private lateinit var iKemana : String

    private lateinit var dataRef : DatabaseReference
    private lateinit var firebaseInstance : FirebaseDatabase
    private lateinit var mDataRef : DatabaseReference
    lateinit var preference : Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tujuan)

        preference = Preference(this)
        iKemana = tv_kemana.text.toString()
        iUsername = tv_namaku.text.toString()
        firebaseInstance = FirebaseDatabase.getInstance()
        dataRef = FirebaseDatabase.getInstance().getReference()
        mDataRef = firebaseInstance.getReference().child("MyTicket").child(preference.getData("username")!!)


        tv_namaku.setText(preference.getData("username"))
        tv_darimana.setOnClickListener {
            val intent = Intent(this,TujuanAwalActivity::class.java)
            startActivityForResult(intent, 10)
        }
        tv_kemana.setOnClickListener {
            val intent2 = Intent(this,DestinasiActivity::class.java)
            startActivityForResult(intent2,13)
        }

        bt_lanjutkan.setOnClickListener {
            iUsername = tv_namaku.text.toString()
            tujuanAwal = tv_darimana.text.toString()
            tempatAwal = tv_rincian1.text.toString()
            tujuanAkhir = tv_kemana.text.toString()
            tempatAkhir = tv_rincian2.text.toString()
            tanggalBerangkat = tv_tanggal.text.toString()
            savePlace(iUsername,tujuanAwal,tempatAwal,tujuanAkhir,tempatAkhir,tanggalBerangkat)
        }

        tv_tanggal.setOnClickListener {
            val newCalendar: Calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                    tv_tanggal.setText(dateFormatter.format(newDate.getTime()))
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10){
            if (resultCode == Activity.RESULT_OK){
                tv_darimana.text = data?.getStringExtra("key") 
            }
        }
        if (requestCode == 13){
            if (resultCode == Activity.RESULT_OK){
                tv_kemana.text = data?.getStringExtra("key1")
            }
        }
    }

    private fun savePlace(iUsername: String,tujuanAwal : String, tujuanAkhir : String, tempatAwal : String, tempatAkhir : String, tanggalBerangkat : String){

        val tiket = Ticket()
        tiket.username = iUsername
        tiket.tujuanAwal = tujuanAwal
        tiket.tempatAwal = tempatAwal
        tiket.tujuanAkhir = tujuanAkhir
        tiket.tempatAkhir = tempatAkhir
        tiket.tanggal = tanggalBerangkat
        addData(tempatAwal,tiket)
    }

    private fun addData(tempatAwal: String, tiket : Ticket){
        mDataRef.child(tempatAwal).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mDataRef.child(tempatAwal).setValue(tiket)

                preference.setData("username", tiket.username).toString()
                preference.setData("tujuanAwal", tiket.tujuanAwal.toString())
                preference.setData("tempatAwal", tiket.tempatAwal.toString())
                preference.setData("tujuanAkhir", tiket.tujuanAkhir.toString())
                preference.setData("tempatAkhir", tiket.tempatAkhir.toString())
                preference.setData("tanggal", tiket.tanggal.toString())


                val intent = Intent(this@TujuanActivity, BusActivity::class.java)
                intent.putExtra("data", tiket)
                startActivity(intent)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TujuanActivity, "database error", Toast.LENGTH_SHORT).show()
            }

        })

    }
}