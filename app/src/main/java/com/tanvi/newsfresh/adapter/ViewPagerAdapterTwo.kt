package com.tanvi.newsfresh.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.model.Article
import retrofit2.http.Url

class ViewPagerAdapterTwo(private val images:List<Int>):RecyclerView.Adapter<ViewPagerAdapterTwo.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder( itemView: View):RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView =itemView.findViewById(R.id.ViewPagerImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.activity_viewpager,parent,false)
        return ViewPagerViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
       val data:Article = images[position]
        Glide.with(holder.itemView.context).load().into(holder.imageView)
    }
    override fun getItemCount(): Int {
      return images.size
    }
}
