package com.example.astrocore.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astrocore.Adapters.MercuryAdapter
import com.example.astrocore.Adapters.VenusAdapter
import com.example.astrocore.Data.PlanetInfo
import com.example.astrocore.R

class VenusActivity : AppCompatActivity() {
    private lateinit var rv : RecyclerView
    private lateinit var newArray : ArrayList<PlanetInfo>
    private lateinit var adapter: VenusAdapter
    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venus)

        val rec=findViewById<RecyclerView>(R.id.rc1)
        rec.layoutManager= LinearLayoutManager(baseContext, RecyclerView.VERTICAL,false)
        btn = findViewById(R.id.btn1)
        btn.setOnClickListener {
            intent = Intent(this,QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        adapter= VenusAdapter(baseContext)
        rec.adapter=adapter
    }
}