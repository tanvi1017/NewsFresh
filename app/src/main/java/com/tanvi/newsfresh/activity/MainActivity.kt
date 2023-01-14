package com.tanvi.newsfresh.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.tanvi.newsfresh.adapter.ViewPagerAdapter
import com.tanvi.newsfresh.databinding.ActivityMainBinding
import com.tanvi.newsfresh.model.Article

val newsArray = arrayOf("technology","Science", "Sport", "healthCare", "entertainment")
   class MainActivity : AppCompatActivity() {
//      companion object{
//           lateinit var dataNew: Article
//       }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = newsArray[position] //0 /
        }.attach()
    }
}