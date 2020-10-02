package com.baharudin.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.travelapp.model.Users
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var databaseRef : DatabaseReference
    private lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        databaseRef = FirebaseDatabase.getInstance().getReference("Users")
        preference = Preference(this)

        bt_masuk.setOnClickListener {

            iUsername = et_email.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername == ""){
                et_email.error ="silahkan isi username"
                et_email.requestFocus()
            }else if ( iPassword == ""){
                et_password.error = "silahkan isi password anda"
                et_password.requestFocus()
            }else{
                getUser(iUsername,iPassword)
            }

        }
        bt_daftar.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
    private fun getUser(iUsername : String, iPassword : String){
        databaseRef.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(Users::class.java)
                if (user == null) {
                    Toast.makeText(
                        this@LoginActivity,
                        "username tidak ditemukan",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (user.password.equals(iPassword)) {
                        preference.setData("nama", user.nama.toString())
                        preference.setData("username", user.username.toString())
                        preference.setData("password", user.password.toString())
                        preference.setData("email", user.email.toString())
                        preference.setData("foto", user.foto.toString())
                        preference.setData("nohp", user.nohp.toString())
                        finishAffinity()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginActivity, "password anda salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })


    }
}