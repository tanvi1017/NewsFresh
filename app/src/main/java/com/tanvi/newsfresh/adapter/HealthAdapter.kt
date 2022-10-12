package com.tanvi.newsfresh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.HealthDataItem
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.databinding.RowHealthBinding

class HealthAdapter (val itemDataList: List<HealthDataItem>) : RecyclerView.Adapter<HealthAdapter.ItemViewHolder>() {
    class ItemViewHolder( val binding: RowHealthBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =RowHealthBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_health, parent, false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model= itemDataList[position]
        holder.binding.ivImage.setImageResource(model.image)
        holder.binding.tvHeadline.text = model.headline
    }
    override fun getItemCount(): Int {
        return itemDataList.size
    }
}

