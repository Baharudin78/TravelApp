package com.baharudin.travelapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.travelapp.adapter.IconAdapter
import com.baharudin.travelapp.model.Icons
import com.baharudin.travelapp.utils.Preference
import kotlinx.android.synthetic.main.activity_identity.*
import kotlin.random.Random

class IdentityActivity : AppCompatActivity() {
   lateinit var preference: Preference
    private var list = generateItem(1)
    private var adapter = IconAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identity)

        preference = Preference(this)
        et_penumpang_email.setText(preference.getData("email"))
        et_no_hp.setText(preference.getData("nohp"))

        rv_identitas.layoutManager = LinearLayoutManager(this)
        rv_identitas.adapter = adapter
        rv_identitas.setHasFixedSize(true)

    }
    fun insertItem(view : View){
        val index = Random.nextInt(1)
        val newItem = Icons(
            R.drawable.ic_user_green,
            R.drawable.ic_right_arrow
        )
        list.add(index,newItem)
        adapter.notifyItemInserted(index)
    }
    fun removeItem(view: View){
        val index = Random.nextInt(1)
        list.removeAt(index)
        adapter.notifyItemRemoved(index)
    }
    fun generateItem(size : Int): ArrayList<Icons>{
        val list = ArrayList<Icons>()
        for (i in 0 until size){
            val item = Icons(R.drawable.ic_user_green,R.drawable.ic_right_arrow)
            list += item
        }
        return list
    }
}