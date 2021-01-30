
package com.baharudin.travelapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.baharudin.travelapp.databinding.ActivitySplashBinding
import com.baharudin.travelapp.onboarding.OnbardingAct
import com.baharudin.travelapp.utils.Preference

class SplashActivity : AppCompatActivity() {

    lateinit var preference : Preference
    lateinit var _binding : ActivitySplashBinding
    private var username_key : String = ""
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        preference = Preference(this)
        getUser()

    }
    private fun getUser(){
        username_key = preference.getData("username")!!
        if (username_key.isEmpty()){
            runnable
        }else{
            startActivity(Intent(this,HomeActivity::class.java))
            finishAffinity()
        }
    }
    private val runnable = Runnable {
        finishAffinity()
        startActivity(Intent(this,OnbardingAct::class.java))
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}