package com.example.astrocore.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.astrocore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    private lateinit var layout1: View
    private lateinit var layout2: View
    private lateinit var layout3: View
    private lateinit var tv2 : TextView
    private lateinit var persontage1: TextView
    private lateinit var persontage2: TextView
    private lateinit var persontage3: TextView
    private lateinit var pb1 : ProgressBar
    private lateinit var pb2 : ProgressBar
    private lateinit var img2_ : ImageView
    private lateinit var img2 : ImageView
    private  var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_home, container, false)
        Log.d("AboutMovieFragment.java", "The onCreateView method called")
        layout1 = rootView.findViewById(R.id.mercurylayout)
        layout2 = rootView.findViewById(R.id.venuslayout)
        layout3 = rootView.findViewById(R.id.earthlayout)
        tv2 = rootView.findViewById(R.id.textView13)
        persontage1 = rootView.findViewById(R.id.mercurypersontage)
        persontage2 = rootView.findViewById(R.id.venuspersontage)
        persontage3 = rootView.findViewById(R.id.earthpersontage)
        img2 = rootView.findViewById(R.id.imageView1)
        img2_ = rootView.findViewById(R.id.imageView10)
        pb1 = rootView.findViewById(R.id.progressBar)
        pb2 = rootView.findViewById(R.id.progressBar1)
        val id = auth.currentUser!!.uid
        db.collection("users").document(id).get().addOnCompleteListener {
            val level = it.result!!.data?.getValue("level").toString()
            val mercury = it.result!!.data?.getValue("mercury").toString()
            val venus = it.result!!.data?.getValue("venus").toString()
            pb1.setProgress(mercury.toInt() ,true)
            persontage1.setText(mercury+"%" )
            if(level.toInt() == 2){
                tv2.visibility = View.GONE
                persontage2.visibility = View.VISIBLE
                img2_.visibility = View.GONE
                img2.visibility = View.VISIBLE
                persontage2.setText(venus+"%")
                pb2.setProgress(venus.toInt()  ,true)
            }
        }
        layout1.setOnClickListener {
            val intent = Intent(this.context,MercuryActivity::class.java)
            intent.putExtra("h","hh")
            startActivity(intent)
        }
        layout2.setOnClickListener {
            val intent = Intent(this.context,VenusActivity::class.java)
            intent.putExtra("h","hh")
            startActivity(intent)
        }
        return rootView


    }
}