package com.example.astrocore.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astrocore.Adapters.MercuryAdapter
import com.example.astrocore.Data.PlanetInfo
import com.example.astrocore.R

class MercuryActivity : AppCompatActivity() {
    private lateinit var rv : RecyclerView
    private lateinit var newArray : ArrayList<PlanetInfo>
    private lateinit var quizButton: Button
    private lateinit var adapter: MercuryAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_mercury)

        quizButton = findViewById(R.id.btn)
        quizButton.setOnClickListener {
            intent =  Intent(this,QuizActivity::class.java)
            intent.putExtra("PLANET_NAME", "mercury")
            startActivity(intent)
        }

        val rec=findViewById<RecyclerView>(R.id.rc)
        rec.layoutManager= LinearLayoutManager(baseContext, RecyclerView.VERTICAL,false)


        adapter=MercuryAdapter(baseContext)
        rec.adapter=adapter
    }
}