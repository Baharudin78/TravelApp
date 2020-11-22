package com.baharudin.travelapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.model.Kursi
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_kursi.*

class KursiActivity : AppCompatActivity() {


    private var statusA1 : Boolean = false
    private var statusA2 : Boolean = false
    private var statusA3 : Boolean = false
    private var statusA4 : Boolean = false
    private var statusA5 : Boolean = false
    private var statusA6 : Boolean = false
    private var statusA7 : Boolean = false
    private var statusA8 : Boolean = false
    private var statusA9 : Boolean = false
    private var total : Int = 0
    private var dataList = ArrayList<Bus>()


    lateinit var preference : Preference

    private lateinit var kursi: Kursi
    private lateinit var tiket: Ticket


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kursi)


        preference = Preference(this)
        val data = intent.getParcelableExtra<Bus>("data1")
        tv_nama_bus.text = data?.travel
        tv_dari.setText(preference.getData("tujuanAwal"))
        tv_tujuan.setText(preference.getData("tempatAwal"))


        iv_a1.setOnClickListener {
            if (statusA1){
                iv_a1.setImageResource(R.drawable.ic_seat_able)
                statusA1 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a1.setImageResource(R.drawable.ic_seat_select)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A1")
                dataList.add(data)
            }
        }

        iv_a2.setOnClickListener {
            if (statusA2){
                iv_a2.setImageResource(R.drawable.ic_seat_able)
                statusA2 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a2.setImageResource(R.drawable.ic_seat_select)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A2")
                dataList.add(data)
            }
        }
        iv_a3.setOnClickListener {
            if (statusA3){
                iv_a3.setImageResource(R.drawable.ic_seat_able)
                statusA3 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a3.setImageResource(R.drawable.ic_seat_select)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A3")
                dataList.add(data)
            }
        }
        iv_a4.setOnClickListener {
            if (statusA4){
                iv_a4.setImageResource(R.drawable.ic_seat_able)
                statusA4 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a4.setImageResource(R.drawable.ic_seat_select)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A4")
                dataList.add(data)
            }
        }
        iv_a5.setOnClickListener {
            if (statusA5){
                iv_a5.setImageResource(R.drawable.ic_seat_able)
                statusA5 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a5.setImageResource(R.drawable.ic_seat_select)
                statusA5 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A5")
                dataList.add(data)
            }
        }
        iv_a6.setOnClickListener {
            if (statusA6){
                iv_a6.setImageResource(R.drawable.ic_seat_able)
                statusA6 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a6.setImageResource(R.drawable.ic_seat_select)
                statusA6 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A6")
                dataList.add(data)
            }
        }
        iv_a7.setOnClickListener {
            if (statusA7){
                iv_a7.setImageResource(R.drawable.ic_seat_able)
                statusA7 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a7.setImageResource(R.drawable.ic_seat_select)
                statusA7 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A7")
                dataList.add(data)
            }
        }
        iv_a8.setOnClickListener {
            if (statusA8){
                iv_a8.setImageResource(R.drawable.ic_seat_able)
                statusA8 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a8.setImageResource(R.drawable.ic_seat_select)
                statusA8 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A8")
                dataList.add(data)
            }
        }
        iv_a9.setOnClickListener {
            if (statusA9){
                iv_a9.setImageResource(R.drawable.ic_seat_able)
                statusA9 = false
                total -= 1
                beliTiket(total)
            }else{
                iv_a9.setImageResource(R.drawable.ic_seat_select)
                statusA9 = true
                total += 1
                beliTiket(total)

                val data = Bus(data?.harga.toString(),"A9")
                dataList.add(data)
            }
        }
        bt_kursi_next.setOnClickListener {
            val intent = Intent(this,IdentityActivity::class.java).putExtra("data",dataList).putExtra("datas",data).putExtra("total",total)
            startActivity(intent)

        }
    }

    private fun beliTiket(total : Int){
        if (total == 0){
            bt_kursi_next.setText("Lanjutkan")
            bt_kursi_next.visibility = View.INVISIBLE
        }else{
            bt_kursi_next.setText("Beli Tiket (" + total + ")")
            bt_kursi_next.visibility = View.VISIBLE
        }
    }
}