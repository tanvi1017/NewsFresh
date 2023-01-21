package com.tanvi.newsfresh.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.ItemData
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.adapter.Adapter
import com.tanvi.newsfresh.databinding.ActivityHomeBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article

class HomeActivity : AppCompatActivity(), NewsClickInterface {
    lateinit var binding: ActivityHomeBinding
    private val listData = mutableListOf<ItemData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
    }

    private fun formRecyclerview() {
        val data1 = ItemData(R.drawable.ic_biotech_24, "technology")
        val data2 = ItemData(R.drawable.ic_science, "Science")
        val data3 = ItemData(R.drawable.ic_sport, "Sport")
        val data4 = ItemData(R.drawable.ic_baseline_health_and_safety_24, "healthCare")
        val data5 = ItemData(R.drawable.ic_baseline_trending_up_24, "Trending")
        val data6 = ItemData(R.drawable.ic_baseline_insert_emoticon_24, "entertainment")
        listData.add(data1)
        listData.add(data2)
        listData.add(data3)
        listData.add(data4)
        listData.add(data5)
        listData.add(data6)
    }

    private fun setAdapter() {
        formRecyclerview()
        binding.rvMenu.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = Adapter(listData, this)
        binding.rvMenu.adapter = adapter
    }

    override fun openNews(newsModel: Article) {
        TODO("Not yet implemented")
    }

    override fun onCellClickListener(position: Int): ItemData {
        TODO("Not yet implemented")
    }

}