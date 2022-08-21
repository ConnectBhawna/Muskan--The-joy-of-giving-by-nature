package com.example.muskan

import com.example.muskan.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {

        /**q
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
        //Adding an action bar
        setSupportActionBar(findViewById(R.id.my_toolbar))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.example.muskan.R.menu.menutoolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> Toast.makeText(this, "Favourite selected", Toast.LENGTH_SHORT)
                .show()
            R.id.action_settings -> Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                .show()
            else -> {}
        }

        return true
    }
}