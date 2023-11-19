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
import com.example.astrocore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var etFname : EditText
    private lateinit var etLname : EditText
    private lateinit var etUsername : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPass : EditText
    private lateinit var etCpass : EditText
    private lateinit var Btlogin : Button
    private lateinit var BtSignup : Button
    private lateinit var progressBar: ProgressBar
    private  var auth : FirebaseAuth = FirebaseAuth.getInstance()
    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_welcome)
        setContentView(R.layout.activity_sign_up)

        etFname = findViewById(R.id.signupfirstname)
        etLname = findViewById(R.id.signuplastname)
        etUsername = findViewById(R.id.signupusername)
        etEmail = findViewById(R.id.signupemail)
        etPass = findViewById(R.id.signuppassword)
        etCpass = findViewById(R.id.signupconfirmpass)
        Btlogin = findViewById(R.id.signuplogin)
        BtSignup = findViewById(R.id.signupsignup)
        progressBar = findViewById(R.id.signupprogressbar)

        Btlogin.setOnClickListener {
            intent=  Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        BtSignup.setOnClickListener {

            if (etLname.text.isEmpty()||etFname.text.isEmpty()||etUsername.text.isEmpty() || etEmail.text.isEmpty() || etPass.text.isEmpty() || etCpass.text.isEmpty() || etPass.text.toString() != etCpass.text.toString() || etPass.text.length < 6|| !etEmail.text.toString().matches(emailPattern.toRegex())){
                if (etUsername.text.isEmpty()){
                    etUsername.error = "Please enter a username"
                }
                if (etEmail.text.isEmpty()){
                    etEmail.error = "Please enter an email"
                }
                if (etPass.text.isEmpty()){
                    etPass.error = "Please enter a password"
                }
                if (etCpass.text.isEmpty()){
                    etCpass.error = "Please enter a confirm password"
                }
                if (etPass.text.toString() != etCpass.text.toString()) {
                    etPass.error
                    etCpass.error
                    Toast.makeText(
                        this,
                        "please make sure you wrote the same password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if(etPass.text.length<6){
                    etPass.error = "Please enter more than 5"
                }

            }else{
                progressBar.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(etEmail.text.toString(),etPass.text.toString()).addOnSuccessListener {
                    val id = auth.currentUser!!.uid
                    val userMap = hashMapOf(
                        "id" to id,
                        "username" to etUsername.text.toString(),
                        "email" to etEmail.text.toString(),
                        "name" to etFname.text.toString() + " " + etLname.text.toString(),
                        "level" to 1,
                        "mercury" to "0",
                        "venus" to "0",
                        "mars" to "0",
                        "jupiter" to "0",
                        "uranus" to "0",
                        "naptune" to "0",
                        "earth" to "0"

                        )
                    db.collection("users").document(id).set(userMap).addOnSuccessListener {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "User created successfully!", Toast.LENGTH_SHORT).show()
                        intent=  Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    }.addOnFailureListener {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Failed chack your informations!", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}