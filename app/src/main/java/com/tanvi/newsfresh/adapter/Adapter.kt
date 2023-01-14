package com.tanvi.newsfresh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.ItemData
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.interfaces.NewsClickInterface

//import sun.java2d.SunGraphics2D.isRotated


   class Adapter(val itemDataList: List<ItemData>,val newsClickListener:NewsClickInterface):RecyclerView.Adapter<Adapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageButton:ImageButton =itemView.findViewById(R.id.imageButton)
        val textView:TextView =itemView.findViewById(R.id.tvTextView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_menu_row, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = itemDataList[position]
        holder.imageButton.setImageResource(data.Image)
        holder.textView.text = data.text
        val context = holder.itemView.getContext()
        holder.imageButton.setOnClickListener {
         //   newsClickListener.onCellClickListener(position)
//        when (getItemViewType(position)) {
//            0 ->
//                intent = Intent(context, TechnologyFragment()::class.java)
//            1 ->
//                intent = Intent(context, ScienceFragment()::class.java)
//            2 ->
//                intent = Intent(context, ScienceFragment()::class.java)
//            3 ->
//                intent = Intent(context, HealthCareFragment()::class.java)
//            5 ->
//                intent = Intent(context, EntertainmentFragment()::class.java)
//         }
           // context.startActivity(intent)
        }
    }
       override fun getItemCount(): Int {
         return itemDataList.size
       }
   }

