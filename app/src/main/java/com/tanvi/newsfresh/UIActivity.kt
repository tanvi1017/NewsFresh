package com.tanvi.newsfresh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UIActivity:AppCompatActivity() {
    lateinit var recyclerView1: RecyclerView
    var listData1 = mutableListOf<ItemData>()
    lateinit var menuCardView:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)
        recyclerView1 =findViewById(R.id.recyclerViewFrag)
        menuCardView=findViewById(R.id.menuCardView)
        menuCardView.setOnClickListener {
            val intent= Intent(this@UIActivity,MainActivity::class.java)
            startActivity(intent)
        }
        formRecyclerview()
        setAdapter()

    }
    private fun setAdapter(){
        recyclerView1.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
       val adapter= MenuAdapter(listData1)
        recyclerView1.adapter=adapter
    }
    private fun formRecyclerview(){
        val data1=ItemData(R.drawable.ic_trending_up_24,"Trending")
        val data2 =ItemData(R.drawable.ic_sports_24,"Sports")
        val data3 =ItemData(R.drawable.ic_biotech_24,"Technology")
        val data4 =ItemData(R.drawable.ic_baseline_health,"HealthCare")
        val data5=ItemData(R.drawable.ic_entertainment,"Entertainment")
        val data6 =ItemData(R.drawable.ic_science_24,"Science")
        listData1.add(data1)
        listData1.add(data2)
        listData1.add(data3)
        listData1.add(data4)
        listData1.add(data5)
        listData1.add(data6)
    }
    }
