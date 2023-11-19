package com.example.astrocore

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DBHelper {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    //signup function
    fun createUSer (email : String , pass : String , userName : String) {
        auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener {
            val userMap = mapOf(
                "email" to email,
                "password" to pass,
                "level" to 1,
                "name" to userName
            )
            db.collection("users").document(auth.currentUser!!.uid).set(userMap).addOnSuccessListener {
                return@addOnSuccessListener
            }.addOnFailureListener {
                return@addOnFailureListener
            }
        }.addOnFailureListener {
            return@addOnFailureListener
        }
    }
    //login function
    fun signIn(email :String , pass : String){
        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener {
            return@addOnSuccessListener
        }.addOnFailureListener {
            return@addOnFailureListener
        }
    }
    //get the user name from the database
    /*fun getName() : String{
        val id = auth.currentUser!!.uid
        db.collection("users").document(id).get().addOnCompleteListener {
           return it.result!!.data?.getValue("name").toString().trim()
        }
    }*/



}