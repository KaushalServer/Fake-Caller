package com.example.callfaker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.callfaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private  val handler = Handler(Looper.getMainLooper())
    private var seconds = 0
    private var running = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Retrieve data from SharedPreferences
        val name = sharedPreferences.getString("name", "Default Name")
        val number = sharedPreferences.getString("number", "Default Number")

        binding.nameTextView.text = name
        binding.numberTextView.text = number

        // Start the timer after 5 seconds
        handler.postDelayed({
            binding.timerTextView.text = "00:00"
            running = true
            runTimer()
        }, 5000)


        binding.endCallBtn.setOnClickListener {
            running = false
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun runTimer() {
        handler.post(object : Runnable {
            override fun run() {
                if (running) {
                    val minutes = seconds / 60
                    val secs = seconds % 60
                    val time = String.format("%02d:%02d", minutes, secs)
                    binding.timerTextView.text = time
                    seconds++
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        running = false
        handler.removeCallbacksAndMessages(null)
    }
}