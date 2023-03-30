package com.tanvi.newsfresh.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanvi.newsfresh.Model.Article
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.util.Utils

class UINewsAdapter(private var article: List<Article>,var context: Context):RecyclerView.Adapter<UINewsAdapter.ItemViewHolder>() {
    class ItemViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image:ImageView=itemView.findViewById(R.id.uiNewsImage)
        val text:TextView =itemView.findViewById(R.id.uiNewsHeading)
        val date:TextView =itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ui_news_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
      val model = article[position]
        val datee = Utils.DateFormat(model.date)
      holder.text.text = model.title
        holder.date.text = datee
      Glide.with(context).load(model.urlToImage).into(holder.image)
    }
    override fun getItemCount(): Int {
       return article.size
    }
}