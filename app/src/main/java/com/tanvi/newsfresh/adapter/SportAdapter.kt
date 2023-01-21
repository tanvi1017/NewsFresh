package com.tanvi.newsfresh.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.SportDataItem
import com.tanvi.newsfresh.activity.MainActivity
import com.tanvi.newsfresh.activity.SecondActivity
import com.tanvi.newsfresh.databinding.RowSportBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article

class SportAdapter (var itemDataList: List<Article>,val newsClickListener:NewsClickInterface) : RecyclerView.Adapter<SportAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding:RowSportBinding): RecyclerView.ViewHolder(binding.root) {
       // val imageView:ImageView=itemView.findViewById(R.id.ivImage)
       // val tvheadlines:TextView =itemView.findViewById(R.id.tvHeadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowSportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

     override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model:Article = itemDataList[position]
         Glide.with(holder.itemView.context)
             .load(model.urlToImage)
             .into(holder.binding.ivImage)
         holder.binding.tvHeadline.text=(model.description)
         val context = holder.itemView.getContext()
         holder.binding.SportCardView.setOnClickListener {
             newsClickListener.openNews(model)

             //MainActivity.dataNew = itemDataList[position]
//             val intent = Intent(context, SecondActivity::class.java)
//             context.startActivity(intent)
         }
         //holder.binding.ivImage.setImageResource(model.image)
         //holder.binding.tvHeadline.text=(model.headLine)
       // holder.imageView.setImageResource(data.image)
       // holder.tvheadlines.text = data.headLine
    }
    override fun getItemCount(): Int {
        return itemDataList.size
    }
   fun updateNewsList(updatedNews:MutableList<Article>){
       (updatedNews as MutableList<Article>).also { itemDataList = it }
       notifyDataSetChanged()
   }

    }
