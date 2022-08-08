package com.example.muskan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {

        /**
         * Simulating a delay, in production app it could be a network call
         * or setting up all resources before setting the content view the activity
         * */
        setKeepOnScreenCondition {
            try {
                Thread.sleep(2000L)
            } catch (e: Exception) { println(e.stackTrace) }
            false
        }
    }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}