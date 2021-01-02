package com.baharudin.travelapp.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.R
import com.baharudin.travelapp.adapter.TiketAdapter
import com.baharudin.travelapp.databinding.FragmentTiketBinding
import com.baharudin.travelapp.model.Ticket
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*


class Tiket : Fragment(R.layout.fragment_tiket) {
    lateinit var dataRef : DatabaseReference
    lateinit var preference: Preference
    var dataList = ArrayList<Ticket>()
    private var _binding : FragmentTiketBinding? = null
    private val binding get() = _binding!!
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTiketBinding.bind(view)

        preference = Preference(requireContext().applicationContext)
        dataRef = FirebaseDatabase.getInstance().getReference("MyTicket").child(preference.getData("username")!!)
        binding.rvRiwayat.layoutManager = LinearLayoutManager(activity)
        getData()
    }
    private fun getData(){
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getData in snapshot.children){
                    val tiket = getData.getValue(Ticket::class.java)
                    dataList.add(tiket!!)
                }
                binding.rvRiwayat.adapter = TiketAdapter(dataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}