package com.tanvi.newsfresh.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanvi.newsfresh.HealthDataItem
import com.tanvi.newsfresh.TechnologyDataItem
import com.tanvi.newsfresh.activity.MainActivity
import com.tanvi.newsfresh.activity.SecondActivity
import com.tanvi.newsfresh.databinding.FragmentTechnologyBinding
import com.tanvi.newsfresh.databinding.RowTechnologyBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article

class TechnologyAdapter(var itemDataList: List<Article>,val newsClickListener:NewsClickInterface): RecyclerView.Adapter<TechnologyAdapter.ItemViewHolder>() {
 class  ItemViewHolder(val binding: RowTechnologyBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowTechnologyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model:Article = itemDataList[position]
        Glide.with(holder.itemView.context)
            .load(model.urlToImage)
            .into(holder.binding.ivImage)
        holder.binding.tvHeadline.text=(model.description)
        val context = holder.itemView.getContext()
        holder.binding.technologyCardView.setOnClickListener {
            //newsClickListener.openNews(model)
           // MainActivity.dataNew =itemDataList[position]
//            val intent = Intent(context, SecondActivity::class.java)
//            context.startActivity(intent)
        }
       // holder.binding.ivImage.setImageResource(model.image)
        //holder.binding.tvHeadline.text = model.headLine
    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }
    fun updateNewsList(updatedNews:MutableList<Article>){
        (updatedNews as MutableList<Article>).also { itemDataList = it }
        notifyDataSetChanged()
    }
}