package com.tanvi.newsfresh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.EntertainmentDataItem
import com.tanvi.newsfresh.databinding.RowEntertainmentBinding

class EntertainmentAdapter(val itemDataList:List<EntertainmentDataItem>) : RecyclerView.Adapter<EntertainmentAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: RowEntertainmentBinding): RecyclerView.ViewHolder(binding.root){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val binding = RowEntertainmentBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ItemViewHolder(binding)
    }
    //10 10 bar call hoga
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = itemDataList[position]
        holder.binding.ivImage.setImageResource(model.images)
        holder.binding.tvHeadline.text = model.headLine

    }
    override fun getItemCount(): Int {
        return itemDataList.size

    }
}
