package com.tanvi.newsfresh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.ScienceDataItem
import com.tanvi.newsfresh.databinding.FragmentScienceBinding
import com.tanvi.newsfresh.databinding.RowScienceBinding

class ScienceAdapter (val itemDataList:List<ScienceDataItem>) : RecyclerView.Adapter<ScienceAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: RowScienceBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowScienceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val model = itemDataList[position]
        holder.binding.ivImage.setImageResource(model.image)
        holder.binding.tvHeadline.text=(model.headline)
    }
    override fun getItemCount(): Int {
       return itemDataList.size
    }
}