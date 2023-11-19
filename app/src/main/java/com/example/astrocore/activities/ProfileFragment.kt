package com.example.astrocore.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.astrocore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log

class ProfileFragment : Fragment() {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var logoutBt :Button
    private lateinit var aboutusBt :Button
    private lateinit var editBt :Button
    private lateinit var helpBt :Button
    private lateinit var tname : TextView
    private lateinit var tuserName : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)
        Log.d("AboutMovieFragment.java", "The onCreateView method called")
        logoutBt = rootView.findViewById(R.id.button4)
        aboutusBt = rootView.findViewById(R.id.button2)
        tname = rootView.findViewById(R.id.textView4)
        tuserName = rootView.findViewById(R.id.textView9)
        editBt = rootView.findViewById(R.id.button)

        val id =auth.currentUser!!.uid
        db.collection("users").document(id).get().addOnCompleteListener {
            val name = it.result!!.data?.getValue("name").toString().trim()
            val uname = it.result!!.data?.getValue("username").toString().trim()
            tname.setText(name)
            tuserName.setText(uname)
        }
        logoutBt.setOnClickListener {
            auth.signOut()
            val intent = Intent(this.context,WelcomeActivity::class.java)
            intent.putExtra("h","hh")
            startActivity(intent)
        }
        aboutusBt.setOnClickListener {
            val intent = Intent(this.context,AboutusActivity::class.java)
            intent.putExtra("h","hh")
            startActivity(intent)
        }
       editBt.setOnClickListener {
            val intent = Intent(this.context,EdituserActivity::class.java)
            intent.putExtra("h","hh")
            startActivity(intent)
        }
        return rootView
    }
}