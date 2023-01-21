package com.tanvi.newsfresh.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanvi.newsfresh.HealthDataItem
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.activity.MainActivity
import com.tanvi.newsfresh.activity.SecondActivity
import com.tanvi.newsfresh.databinding.RowHealthBinding
import com.tanvi.newsfresh.fragment.HealthCareFragment
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article

class HealthAdapter (var itemDataList: List<Article>, val newsClickListener: NewsClickInterface): RecyclerView.Adapter<HealthAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: RowHealthBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowHealthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model: Article = itemDataList[position]
        Glide.with(holder.itemView.context).load(model.urlToImage)
            .into(holder.binding.ivImage)
        holder.binding.tvHeadline.text = (model.description)
        val context = holder.itemView.getContext()
        holder.binding.healthCardView.setOnClickListener {
           // MainActivity.dataNew = itemDataList[position]
//            val intent = Intent(context, SecondActivity::class.java)
//            context.startActivity(intent)
            newsClickListener.openNews(model)
        }
    }
     override fun getItemCount(): Int {
        return itemDataList.size
    }

   fun updateNewsList(updateNews: MutableList<Article>) {
      (updateNews as MutableList<Article>).also { itemDataList = it }
      notifyDataSetChanged()

    }
}


