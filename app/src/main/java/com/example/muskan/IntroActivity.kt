package com.example.muskan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.muskan.databinding.ActivityIntroBinding
import com.example.muskan.introScreen.IntroSliderAdapter

class IntroActivity : AppCompatActivity() {
    lateinit var binding :ActivityIntroBinding ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(LayoutInflater.from(this))
        binding.lifecycleOwner = this
        setContentView(binding.root)
        init()
        clickListeners()
    }
    private fun init(){
        binding.pager.adapter = IntroSliderAdapter(this)
        binding.tabLayout.setupWithViewPager(binding.pager)
    }

    private fun clickListeners(){
        binding.getStarted.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}