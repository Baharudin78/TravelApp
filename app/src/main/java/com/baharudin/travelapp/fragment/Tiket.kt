package com.baharudin.travelapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.R
import com.baharudin.travelapp.TicketDetailActivity
import com.baharudin.travelapp.adapter.TiketAdapter
import com.baharudin.travelapp.databinding.FragmentTiketBinding
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*


class Tiket : Fragment(R.layout.fragment_tiket) {

    lateinit var dataRef : DatabaseReference
    lateinit var preference: Preference
    private var _binding : FragmentTiketBinding? = null
    private val binding get() = _binding!!
    var dataList = ArrayList<Ticket>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTiketBinding.bind(view)



        preference = Preference(requireContext().applicationContext)
        dataRef = FirebaseDatabase.getInstance().getReference("MyTicket")

        binding.rvRiwayat.layoutManager = LinearLayoutManager(activity)
        getData()
        showProgressBar()



    }
    private fun getData(){
        dataRef.orderByKey().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getData in snapshot.children){
                    val tiket = getData.getValue(Ticket::class.java)
                    hideProgressBar()
                    dataList.add(tiket!!)
                }
                binding.rvRiwayat.adapter = TiketAdapter(dataList){
                    val gotoTiket = Intent(requireContext(), TicketDetailActivity::class.java)
                    gotoTiket.putExtra("data_tiket",it)
                    startActivity(gotoTiket)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun showProgressBar() {
        binding.progressBar3.visibility = View.VISIBLE
    }
    private fun hideProgressBar() {
        binding.progressBar3.visibility = View.INVISIBLE
    }
}