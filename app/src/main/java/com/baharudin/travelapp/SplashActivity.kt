
package com.baharudin.travelapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.baharudin.travelapp.databinding.ActivitySplashBinding
import com.baharudin.travelapp.onboarding.OnbardingAct

class SplashActivity : AppCompatActivity() {
    lateinit var _binding : ActivitySplashBinding
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(_binding.root)

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