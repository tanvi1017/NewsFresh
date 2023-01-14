package com.tanvi.newsfresh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanvi.newsfresh.databinding.RowEntertainmentBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article

   class EntertainmentAdapter(var itemDataList:List<Article>, val newClickListener:NewsClickInterface) : RecyclerView.Adapter<EntertainmentAdapter.ItemViewHolder>() {
     class ItemViewHolder(val binding: RowEntertainmentBinding) :
         RecyclerView.ViewHolder(binding.root) {
     }
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
         val binding = RowEntertainmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return ItemViewHolder(binding)
     }
     override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
         val data: Article = itemDataList[position]
         Glide.with(holder.itemView.context).load(data.urlToImage).into(holder.binding.ivImage)
         holder.binding.tvHeadline.text = (data.description)
        // val context = holder.itemView.getContext()
         holder.binding.cardView.setOnClickListener {
           //  newClickListener.openNews(data)
         }
     }
     override fun getItemCount(): Int {
         return itemDataList.size
     }
   fun updateNewsList(updateNews: MutableList<Article>){
       (updateNews as MutableList<Article>).also { itemDataList = it }
    notifyDataSetChanged()
  }
   }

