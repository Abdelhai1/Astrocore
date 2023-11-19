package com.example.astrocore.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.astrocore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ResultActivity : AppCompatActivity() {
    lateinit var button: Button
    private  var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private  var auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_result)
        button = findViewById(R.id.return_home_button)
        button.setOnClickListener {
            val updates = hashMapOf<String,Any>(
                "level" to 2,
                "mercury" to "100"
            )
            db.collection("users").document(auth.currentUser!!.uid).update(updates).addOnSuccessListener {
                intent =  Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }

        }
    }
}