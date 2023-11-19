package com.example.astrocore.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.astrocore.DBHelper
import com.example.astrocore.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail : EditText
    private lateinit var etPass : EditText
    private lateinit var loginBt: Button
    private lateinit var signupBt : Button
    private lateinit var progressBar: ProgressBar
    private  var auth : FirebaseAuth = FirebaseAuth.getInstance()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_welcome)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.loginemail)
        etPass = findViewById(R.id.loginpass)
        loginBt = findViewById(R.id.loginloginbutton)
        signupBt = findViewById(R.id.loginsignupbutton)
        progressBar = findViewById(R.id.loginprogressbar)

        loginBt.setOnClickListener {
            if ( etEmail.text.isEmpty() || etPass.text.isEmpty() ){

                if (etEmail.text.isEmpty()){
                    etEmail.error = "Please enter an email"
                }
                if (etPass.text.isEmpty()) {
                    etPass.error = "Please enter a password"
                }}else{
                    progressBar.visibility = View.VISIBLE
                    auth.signInWithEmailAndPassword(etEmail.text.toString(),etPass.text.toString()).addOnSuccessListener {
                    progressBar.visibility = View.GONE
                    intent=  Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Logged in successfully!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    progressBar.visibility = View.GONE
                    Toast.makeText(this,"Failed check your informations!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        signupBt.setOnClickListener {
            intent=  Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }



    }
}