package com.tasty.recipesapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity"
    private lateinit var binding: ActivitySplashBinding
    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds





    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("SplashActivity", "onCreate: SplashActivity created.")

//        // Set click listener for Start button
//        binding.startButton.setOnClickListener {
//            val message = binding.editText.text.toString()
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("message", message)
//            startActivity(intent)
//        }


        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Delay and transition to MainActivity
        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: SplashActivity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: SplashActivity resumed.")

        // Navigate to MainActivity after a delay (simulate a splash screen delay)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 10000) // 2 seconds delay
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: SplashActivity paused.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: SplashActivity stopped.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: SplashActivity destroyed.")
    }
}