package com.baharudin.travelapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.baharudin.travelapp.databinding.ActivityKursiBinding
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*

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
    lateinit var namaBus : String
    private var dataList = ArrayList<Bus>()
    lateinit var binding : ActivityKursiBinding
    lateinit var preference : Preference

    lateinit var tiket : Ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKursiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)

        val data = intent.getParcelableExtra<Bus>("data1")
        binding.tvNamaBus.text = data?.travel

        val bus = data?.travel
        preference.setData("bus",bus.toString())
        binding.tvDari.setText(preference.getData("tujuanAwal"))
        binding.tvTujuan.setText(preference.getData("tempatAwal"))

        namaBus = binding.tvNamaBus.text.toString()

        binding.ivA1.setOnClickListener {
            if (statusA1){
                binding.ivA1.setImageResource(R.drawable.ic_seat_able)
                statusA1 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA1.setImageResource(R.drawable.ic_seat_select)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Bus("A1",data!!.harga.toString())
                dataList.add(data)
            }
        }

        binding.ivA2.setOnClickListener {
            if (statusA2){
                binding.ivA2.setImageResource(R.drawable.ic_seat_able)
                statusA2 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA2.setImageResource(R.drawable.ic_seat_select)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = Bus("A2",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.ivA3.setOnClickListener {
            if (statusA3){
                binding.ivA3.setImageResource(R.drawable.ic_seat_able)
                statusA3 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA3.setImageResource(R.drawable.ic_seat_select)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Bus("A3",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.ivA4.setOnClickListener {
            if (statusA4){
                binding.ivA4.setImageResource(R.drawable.ic_seat_able)
                statusA4 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA4.setImageResource(R.drawable.ic_seat_select)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Bus("A4",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.ivA5.setOnClickListener {
            if (statusA5){
                binding.ivA5.setImageResource(R.drawable.ic_seat_able)
                statusA5 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA5.setImageResource(R.drawable.ic_seat_select)
                statusA5 = true
                total += 1
                beliTiket(total)

                val data = Bus("A5",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.ivA6.setOnClickListener {
            if (statusA6){
                binding.ivA6.setImageResource(R.drawable.ic_seat_able)
                statusA6 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA6.setImageResource(R.drawable.ic_seat_select)
                statusA6 = true
                total += 1
                beliTiket(total)

                val data = Bus("A6",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.ivA7.setOnClickListener {
            if (statusA7){
                binding.ivA7.setImageResource(R.drawable.ic_seat_able)
                statusA7 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA7.setImageResource(R.drawable.ic_seat_select)
                statusA7 = true
                total += 1
                beliTiket(total)

                val data = Bus("A7",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.ivA8.setOnClickListener {
            if (statusA8){
                binding.ivA8.setImageResource(R.drawable.ic_seat_able)
                statusA8 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA8.setImageResource(R.drawable.ic_seat_select)
                statusA8 = true
                total += 1
                beliTiket(total)

                val data = Bus("A8",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.ivA9.setOnClickListener {
            if (statusA9){
                binding.ivA9.setImageResource(R.drawable.ic_seat_able)
                statusA9 = false
                total -= 1
                beliTiket(total)
            }else{
                binding.ivA9.setImageResource(R.drawable.ic_seat_select)
                statusA9 = true
                total += 1
                beliTiket(total)

                val data = Bus("A9",data!!.harga.toString())
                dataList.add(data)
            }
        }
        binding.btKursiNext.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java).putExtra("data",dataList).putExtra("datas",data)
            startActivity(intent)

        }
    }

    private fun beliTiket(total : Int){
        if (total == 0){
            binding.btKursiNext.setText("Lanjutkan")
            binding.btKursiNext.visibility = View.INVISIBLE
        }else{
            binding.btKursiNext.setText("Beli Tiket (" + total + ")")
            binding.btKursiNext.visibility = View.VISIBLE
        }
    }

}