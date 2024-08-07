package com.example.callfaker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.callfaker.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        binding.submitBtn.setOnClickListener{
            val name = binding.callerName.text.toString()
            val number = binding.callerNumber.text.toString()

            with(sharedPreferences.edit()) {
                putString("name", name)
                putString("number", number)
                apply()
            }

            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}