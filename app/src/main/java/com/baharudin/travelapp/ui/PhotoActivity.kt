package com.baharudin.travelapp.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.baharudin.travelapp.R
import com.baharudin.travelapp.databinding.ActivityPhotoBinding
import com.baharudin.travelapp.model.Users
import com.baharudin.travelapp.utils.Preference
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class PhotoActivity : AppCompatActivity() {


    private var statusAdd = false
    private lateinit var filePath : Uri

    private lateinit var storage : FirebaseStorage
    private lateinit var storageRef : StorageReference
    private lateinit var preference: Preference
    lateinit var dataRef : DatabaseReference
    private lateinit var mFIrebaseInstance : FirebaseDatabase
    lateinit var users: Users
    lateinit var binding : ActivityPhotoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = Preference(this)
        storage = FirebaseStorage.getInstance()
        mFIrebaseInstance = FirebaseDatabase.getInstance()
        storageRef = storage.reference
        dataRef = mFIrebaseInstance.getReference("Users")

        users = intent.getParcelableExtra("data")!!

       binding.tvHello.text = "Selamat datang\n " +users.username

        binding.ivAddFoto.setOnClickListener {
            if (statusAdd){
                statusAdd = false

                binding.btSaveFoto.visibility = View.VISIBLE
                binding.ivAddFoto.setImageResource(R.drawable.ic_add_24)
                binding.ivFoto.setImageResource(R.drawable.ic_person)
            }else{
                ImagePicker.with(this)
                    .galleryOnly()
                    .start()
            }
        }
        binding.btLewatiFoto.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.btSaveFoto.setOnClickListener {
            if (filePath != null){
                val progressBar = ProgressDialog(this)
                progressBar.setTitle("Uploading...")
                progressBar.show()

                val ref = storageRef.child("images/"+UUID.randomUUID().toString())
                    ref.putFile(filePath)
                        .addOnSuccessListener {
                            progressBar.dismiss()
                            Toast.makeText(this, "Uploading berhasil", Toast.LENGTH_SHORT).show()

                            ref.downloadUrl.addOnSuccessListener {
                                preference.setData("foto",it.toString())
                                saveToFirebase(it.toString())
                            }
                        }
                        .addOnFailureListener{
                            progressBar.dismiss()
                            Toast.makeText(this, "Uploading gagal", Toast.LENGTH_SHORT).show()
                        }
                        .addOnProgressListener {
                            taskSnapsot -> val progress = 100.0 * taskSnapsot.bytesTransferred / taskSnapsot.totalByteCount
                            progressBar.setMessage("Upload " + progress.toInt()+ "%")
                        }
            }
        }

    }

    private fun saveToFirebase(url : String) {
        dataRef.child(users.username!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                users.foto = url

                dataRef.child(users.username!!).setValue(users)

                preference.setData("nama",users.nama.toString())
                preference.setData("username",users.username.toString())
                preference.setData("password",users.password.toString())
                preference.setData("email",users.email.toString())
                preference.setData("nohp",users.nohp.toString())
                preference.setData("foto","")
                preference.setData("status","1")
                preference.setData("foto",url)
                finishAffinity()
                startActivity(Intent(this@PhotoActivity, HomeActivity::class.java))
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PhotoActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "anda menekan tobol kembali", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            filePath = data?.data!!
            statusAdd = true

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivFoto)

            Log.i("tamvan","foto berhasil ")
            binding.btSaveFoto.visibility = View.VISIBLE
            binding.ivAddFoto.setImageResource(R.drawable.ic_baseline_delete_24)
        }else if (requestCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "tugas telah dibatalkan", Toast.LENGTH_SHORT).show()
        }
    }

}