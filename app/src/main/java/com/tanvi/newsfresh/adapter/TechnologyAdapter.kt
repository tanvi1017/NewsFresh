package com.tanvi.newsfresh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.HealthDataItem
import com.tanvi.newsfresh.TechnologyDataItem
import com.tanvi.newsfresh.databinding.FragmentTechnologyBinding
import com.tanvi.newsfresh.databinding.RowTechnologyBinding

class TechnologyAdapter(val itemDataList: List<TechnologyDataItem>): RecyclerView.Adapter<TechnologyAdapter.ItemViewHolder>() {
 class  ItemViewHolder(val binding: RowTechnologyBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowTechnologyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = itemDataList[position]
        holder.binding.ivImage.setImageResource(model.image)
        holder.binding.tvHeadline.text = model.headLine

    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }
}