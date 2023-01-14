package com.tanvi.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.databinding.ItemNewsBinding
import com.tanvi.newsfresh.model.News

class NewsAdapterClass(private val listener: NewsItemClicked) :
    RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        //holder.titleView.text = currentItem.title
    }
    override fun getItemCount(): Int {
        return items.size
    }
}
class NewsViewHolder(binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    val titleView: TextView = itemView.findViewById(R.id.title)
}
interface NewsItemClicked {
    fun onItemClicked(item: String)
}
