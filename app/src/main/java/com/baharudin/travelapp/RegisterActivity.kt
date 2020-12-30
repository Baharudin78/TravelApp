package com.baharudin.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.travelapp.databinding.ActivityRegisterBinding
import com.baharudin.travelapp.model.Users
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var iEmail : String
    private lateinit var iHP : String

    private lateinit var dataRef : DatabaseReference
    private lateinit var firebaseInstance : FirebaseDatabase
    private lateinit var preference: Preference
    private lateinit var databaseRef : DatabaseReference
    lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseInstance = FirebaseDatabase.getInstance()
        dataRef = FirebaseDatabase.getInstance().getReference("Users")
        databaseRef = firebaseInstance.getReference("Users")
        preference = Preference(this)


        binding.btDaftarNext.setOnClickListener {
            iUsername = binding.etDaftarUser.text.toString()
            iPassword = binding.etDaftarPassword.text.toString()
            iEmail = binding.etDaftarEmail.text.toString()
            iHP = binding.etDaftarHp.text.toString()

            if (iUsername == ""){
                binding.etDaftarUser.error ="mohon isi username anda"
                binding.etDaftarUser.requestFocus()
            }else if (iPassword == ""){
                binding.etDaftarPassword.error ="isi password anda"
                binding.etDaftarPassword.requestFocus()
            }else if (iEmail == ""){
                binding.etDaftarEmail.error ="mohon isikan email anda"
                binding.etDaftarEmail.requestFocus()
            }else if (iHP == ""){
                binding.etDaftarHp.error = "isikan no hp anda"
                binding.etDaftarHp.requestFocus()
            }else{
                saveUser(iUsername,iPassword,iEmail,iHP)
            }
        }
    }

    private fun saveUser(iUsername: String, iPassword: String, iEmail: String, iHP: String) {
        val user = Users()

        user.username = iUsername
        user.password = iPassword
        user.email = iEmail
        user.nohp = iHP

        if (iUsername != null){
            checkUser(iUsername, user)
        }
       

    }

    private fun checkUser(iUsername: String, data : Users) {
        databaseRef.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(Users::class.java)

                if (user == null) {
                    databaseRef.child(iUsername).setValue(data)
                    preference.setData("nama", data.nama.toString())
                    preference.setData("username", data.username.toString())
                    preference.setData("password", data.password.toString())
                    preference.setData("email", data.email.toString())
                    preference.setData("foto", "")
                    preference.setData("nohp", data.nohp.toString())
                    preference.setData("status", "1")

                    val goRegister =
                        Intent(this@RegisterActivity, PhotoActivity::class.java)
                        goRegister.putExtra("data",data)
                    startActivity(goRegister)
                } else {
                    Toast.makeText(this@RegisterActivity, "username sudah digunakkan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RegisterActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}