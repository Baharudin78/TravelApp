package com.baharudin.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.travelapp.databinding.ActivityLoginBinding
import com.baharudin.travelapp.model.Users
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var databaseRef : DatabaseReference
    private lateinit var preference: Preference
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseRef = FirebaseDatabase.getInstance().getReference("Users")
        preference = Preference(this)

        preference.setData("onboarding1","1")
        if (preference.getData("status").equals("1")){
            finishAffinity()
            startActivity(Intent(this,HomeActivity::class.java))
        }

        binding.btMasuk.setOnClickListener {

            iUsername = binding.etEmail.text.toString()
            iPassword = binding.etPassword.text.toString()

            if (iUsername == ""){
                binding.etEmail.error ="silahkan isi username"
                binding.etEmail.requestFocus()
            }else if ( iPassword == ""){
                binding.etPassword.error = "silahkan isi password anda"
                binding.etPassword.requestFocus()
            }else{
                getUser(iUsername,iPassword)
            }

        }
        binding.btDaftar.setOnClickListener {
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
                        preference.setData("status", "1")
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