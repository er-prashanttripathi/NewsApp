package com.example.android.newslyrecyclerviewretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivityLinearScrollBinding
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_menu)
        binding.apply {
            btnList.setOnClickListener {
                val intent = Intent(this@MainMenuActivity, LinearScrollActivity::class.java)
                startActivity(intent)
            }
            btnGrid.setOnClickListener {
                val intent = Intent(this@MainMenuActivity, GridActivity::class.java)
                startActivity(intent)
            }
            btnCorousal.setOnClickListener {
                val intent = Intent(this@MainMenuActivity, SwapCorousalActivity::class.java)
                startActivity(intent)
            }
        }

    }
}