package com.baharudin.travelapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.CheckoutAdapter
import com.baharudin.travelapp.databinding.ActivityCheckoutBinding
import com.baharudin.travelapp.model.Bus
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*

class CheckoutActivity : AppCompatActivity() {

    lateinit var preference: Preference
    private var dataList = ArrayList<Bus?>()
    lateinit var binding : ActivityCheckoutBinding
    private var total : Int = 0
    lateinit var tiket : Ticket
    private lateinit var tempatAkhir: String
    lateinit var dataRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)
        dataRef = FirebaseDatabase.getInstance().getReference("MyTicket").child(preference.getData("username")!!)
        tiket = Ticket()
        tempatAkhir = binding.tvTujuanAkhir.text.toString()
        dataList = intent.getSerializableExtra("data") as ArrayList<Bus?>
        val data = intent.getParcelableExtra<Bus>("datas")

        binding.tvTujuanAwal.text = preference.getData("tujuanAwal")
        binding.tvTujuanAkhir.text = preference.getData("tempatAwal")
        binding.tvTanggal.text = preference.getData("tanggal")

        for (a in dataList.indices){
            total += dataList[a]?.harga!!.toInt()
        }

        dataList.add(Bus("Total yang harus dibayar ",total.toString()))
        binding.rvDetail.layoutManager = LinearLayoutManager(this)
        binding.rvDetail.adapter = CheckoutAdapter(dataList){

        }
        binding.btNextKonfirmasi.setOnClickListener {
            saveTotal(total)
            showNotif(data!!)
        }
    }
    private fun saveTotal(total : Int) {
        val ticket = Ticket()
        ticket.total = total.toString()
        uploadTotal(total)
    }
    private fun uploadTotal(total: Int){
        dataRef.child(preference.getData("tempatAwal")!!).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataRef.child(tempatAkhir).setValue(tiket)
                tiket.total = total.toString()
                preference.setData("total",tiket.total.toString())

                val intent = Intent(this@CheckoutActivity,SuccesAct::class.java)
                startActivity(intent)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CheckoutActivity, "failed", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun showNotif(datas : Bus){
        val NOTIFICATION_CHANELL_ID = "Travel Sadam Jaya"
        val context = this.applicationContext
        var notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val channel_name = "notif travel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANELL_ID,channel_name,importance)
            notificationManager.createNotificationChannel(mChannel)
        }
        val intent = Intent(this,TicketAct::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data",datas)
        intent.putExtras(bundle)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this,NOTIFICATION_CHANELL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.mipmap.ic_launcher
                )
            )
            .setTicker("Notif Order Tiket")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
            .setLights(Color.GREEN,3000,3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Sukses Dipesan")
            .setContentText("Tiket travel ${datas.travel} berhasil dipesan.Silahkan Konfirmasi")
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115,builder.build())
    }
}