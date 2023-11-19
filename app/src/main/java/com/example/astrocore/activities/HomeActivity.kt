package com.example.astrocore.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.astrocore.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val tag = "HomeActivity.kt"
    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var galaxyFragment: GalaxyFragment

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_home)

        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        galaxyFragment = GalaxyFragment()

        initializeUI()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit()

    }

    private fun initializeUI() {
        val bottomNavigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationBar.selectedItemId = R.id.Home
        bottomNavigationBar.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.Galaxy -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, galaxyFragment)
                        .commit()
                    Log.d(tag, "Galaxy fragment set!")
                    true
                }
                R.id.Home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, homeFragment)
                        .commit()
                    true
                }
                R.id.Profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer,profileFragment)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}