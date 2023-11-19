package com.example.astrocore.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image.Plane
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrocore.Data.PlanetInfo
import com.example.astrocore.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class MercuryAdapter(var c: Context) : RecyclerView.Adapter<MercuryAdapter.MyViewHolder>() {
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

                            val info1 = PlanetInfo("Mercury","https://akm-img-a-in.tosshub.com/indiatoday/images/story/202205/mercury_2.jpg?VersionId=l15tc8t4WEfbKMdnSLQm3UCtHa8yz42M&size=690:388","Mercury is the smallest and closest planet to the Sun in our solar system. It's a terrestrial planet with extreme temperature variations, ranging from scorching hot to extremely cold. It lacks an atmosphere and has a heavily cratered surface.")
                            postsList.add(info1)
                            val info4 = PlanetInfo("Lava Channels","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSN7jM4wjNpdz1GiUi7NS2s-QIEUyeNuWxcw&usqp=CAU","Evidence of ancient lava flows can be seen in the form of long channels carved into the surface. These channels suggest the movement of molten rock.")
                            val info3 = PlanetInfo("Catenae","https://ichef.bbci.co.uk/news/640/mcs/media/images/82666000/jpg/_82666307_ganymede.jpg","These are chains of craters formed by the impact of a single body that broke apart prior to impact. They are commonly found on Mercury.")
                            val info2 = PlanetInfo("Volcanic Plains","https://planetary.s3.amazonaws.com/web/assets/pictures/_1200x630_crop_center-center_82_none/20180517_messenger-19-northernplains_2015.jpg?mtime=1586175221","Mercury's surface is covered with vast expanses of volcanic plains, suggesting extensive volcanic activity in its early history.")
                            postsList.add(info2)
                            postsList.add(info3)
                            postsList.add(info4)
                            notifyDataSetChanged()

    }
}