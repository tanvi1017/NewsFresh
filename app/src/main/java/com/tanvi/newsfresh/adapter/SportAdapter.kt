package com.tanvi.newsfresh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.SportDataItem
import com.tanvi.newsfresh.databinding.RowSportBinding

class SportAdapter (val itemDataList: List<SportDataItem>) : RecyclerView.Adapter<SportAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding:RowSportBinding): RecyclerView.ViewHolder(binding.root) {
       // val imageView:ImageView=itemView.findViewById(R.id.ivImage)
       // val tvheadlines:TextView =itemView.findViewById(R.id.tvHeadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowSportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

     override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = itemDataList[position]
         holder.binding.ivImage.setImageResource(model.image)
         holder.binding.tvHeadline.text=(model.headLine)
       // holder.imageView.setImageResource(data.image)
       // holder.tvheadlines.text = data.headLine
    }
    override fun getItemCount(): Int {
        return itemDataList.size
    }
}