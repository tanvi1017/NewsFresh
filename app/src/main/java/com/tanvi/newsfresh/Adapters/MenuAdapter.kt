package com.tanvi.newsfresh.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.Model.ItemData
import com.tanvi.newsfresh.R

class MenuAdapter(private val mList:List<ItemData> ):RecyclerView.Adapter<MenuAdapter.ItemViewHolder>() {
    class ItemViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageFrag)
        val textView: TextView = itemView.findViewById(R.id.textFrag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ui_row_frag, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data =mList[position]
        holder.imageView.setImageResource(data.Image)
        holder.textView.text=data.title

    }

    override fun getItemCount(): Int {
       return mList.size
    }

}