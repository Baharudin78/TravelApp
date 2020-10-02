package com.baharudin.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.baharudin.travelapp.model.Users
import com.baharudin.travelapp.utils.Preference
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var iUsername : String
    private lateinit var iPassword : String
    private lateinit var iEmail : String
    private lateinit var iHP : String

    private lateinit var dataRef : DatabaseReference
    private lateinit var firebaseInstance : FirebaseDatabase
    private lateinit var preference: Preference
    private lateinit var databaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseInstance = FirebaseDatabase.getInstance()
        dataRef = FirebaseDatabase.getInstance().getReference("Users")
        databaseRef = firebaseInstance.getReference("Users")
        preference = Preference(this)


        bt_daftar_next.setOnClickListener {
            iUsername = et_daftar_user.text.toString()
            iPassword = et_daftar_password.text.toString()
            iEmail = et_daftar_email.text.toString()
            iHP = et_daftar_hp.text.toString()

            if (iUsername == ""){
                et_daftar_user.error ="mohon isi username anda"
                et_daftar_user.requestFocus()
            }else if (iPassword == ""){
                et_daftar_password.error ="isi password anda"
                et_daftar_password.requestFocus()
            }else if (iEmail == ""){
                et_daftar_email.error ="mohon isikan email anda"
                et_daftar_email.requestFocus()
            }else if (iHP == ""){
                et_daftar_hp.error = "isikan no hp anda"
                et_daftar_hp.requestFocus()
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