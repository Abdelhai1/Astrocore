package com.example.astrocore.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.WindowCompat
import com.example.astrocore.R
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {
    private lateinit var signUpButton : Button
    private lateinit var loginButton: Button
    private  var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_welcome)
        loginButton = findViewById(R.id.alreadyHaveAccButton)
        signUpButton = findViewById(R.id.getStartedButton)

        signUpButton.setOnClickListener {
            intent=  Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
            intent=  Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null) {
            intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }}
}