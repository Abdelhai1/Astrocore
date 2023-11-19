package com.example.astrocore.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrocore.Data.PlanetInfo
import com.example.astrocore.R
import com.google.firebase.firestore.FirebaseFirestore

 @SuppressLint("NotifyDataSetChanged")
 class VenusAdapter(var c: Context) : RecyclerView.Adapter<VenusAdapter.MyViewHolder>() {
    private  var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    companion object {
        var postsList=ArrayList<PlanetInfo>()
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tname : TextView = itemView.findViewById(R.id.infoname)
        val ttext : TextView = itemView.findViewById(R.id.infodescription)
        val pimg : ImageView = itemView.findViewById(R.id.infoimg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.planet_info,parent,false)
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = postsList[position]
        holder.tname.text = currentItem.name
        holder.ttext.text = currentItem.description
        Glide.with(c).load(currentItem.img).into(holder.pimg)
    }

    init {
        postsList.clear()

        val info1 = PlanetInfo("Venus","https://cdn.mos.cms.futurecdn.net/kaPwBjHiUKax8syodHNPmF.jpg","Venus is the second planet from the Sun in our solar system. It's similar in size and composition to Earth, often called Earth's \"sister planet.\" However, Venus experiences extreme temperatures due to a thick, toxic atmosphere that traps heat, making it the hottest planet. The surface is rocky with vast plains, mountains, and volcanoes. Venus is also known for its retrograde rotation, meaning it spins in the opposite direction of most other planets.")
        postsList.add(info1)
        val info4 = PlanetInfo("Venus Water","https://www.wondriumdaily.com/wp-content/uploads/2020/05/A-Field-Guide-to-the-Planets-_Venus%E2%80%99s-Surface-and-Its-Natural-Phenomena_QBS_Thumb.jpg","NASA's Goddard Institute for Space Studies and others have postulated that Venus may have had a shallow ocean in the past for up to 2 billion years, with as much water as Earth. Depending on the parameters used in their theoretical model, the last liquid water could have evaporated as recently as 715 million years ago")
        val info3 = PlanetInfo("Surface","https://media.cnn.com/api/v1/images/stellar/prod/230315153013-04-venus-volcanic-activity.jpg?c=original","Most of the Venusian surface is relatively flat; it is divided into three topographic units: lowlands, highlands, and plains. In the early days of radar observation the highlands drew comparisons to the continents of Earth, but modern research has shown that this is superficial and the absence of plate tectonics makes this comparison misleading")
        val info2 = PlanetInfo("Earth Tween","https://sm.mashable.com/t/mashable_in/photo/default/shutterstock-163793456_mh4f.1248.jpg","Venus is a planet with striking geology. Of all the other planets in the Solar System, it is the one nearest to Earth and most like it in terms of mass, but has no magnetic field or recognizable plate tectonic system")
        postsList.add(info2)
        postsList.add(info3)
        postsList.add(info4)
        notifyDataSetChanged()

    }
}