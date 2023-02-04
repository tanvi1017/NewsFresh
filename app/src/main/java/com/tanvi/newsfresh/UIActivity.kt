package com.tanvi.newsfresh

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.Model.Article
import com.tanvi.newsfresh.Model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UIActivity:AppCompatActivity() {
    private var arguments: Bundle? = null
    lateinit var recyclerView1: RecyclerView
    lateinit var recyclerView2: RecyclerView
    var listData1 = mutableListOf<ItemData>()
    lateinit var menuCardView: CardView
     lateinit var uiAdapter: UINewsAdapter
    private var articles: MutableList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)
        var isTopNews = false
        recyclerView2 = findViewById(R.id.recyclerViewUiNews)
        recyclerView1 = findViewById(R.id.recyclerViewFrag)
        menuCardView = findViewById(R.id.menuCardView)
        isTopNews = arguments?.getBoolean(FragmentTopNews.IS_TOP_NEWS) ?: false
        menuCardView.setOnClickListener {
            val intent = Intent(this@UIActivity, MainActivity::class.java)
            startActivity(intent)
        }
        formRecyclerview()
        setAdapter()
        callApi()
    }
    private fun setAdapter() {
        recyclerView1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = MenuAdapter(listData1)
        recyclerView1.adapter = adapter
    }
    private fun formRecyclerview() {
        val data1 = ItemData(R.drawable.ic_trending_up_24, "Trending")
        val data2 = ItemData(R.drawable.ic_sports_24, "Sports")
        val data3 = ItemData(R.drawable.ic_biotech_24, "Technology")
        val data4 = ItemData(R.drawable.ic_baseline_health, "HealthCare")
        val data5 = ItemData(R.drawable.ic_entertainment, "Entertainment")
        val data6 = ItemData(R.drawable.ic_science_24, "Science")
        listData1.add(data1)
        listData1.add(data2)
        listData1.add(data3)
        listData1.add(data4)
        listData1.add(data5)
        listData1.add(data6)
    }
    private fun callApi(){
        val apiInterface=ApiClient.apiClient.create(ApiInterface::class.java)
        val call: Call<News> = apiInterface.getNews("trending",Constant.API_KEY,50,1)
        call.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful)
                {
                    articles.addAll(response.body()!!.articles)
                    SetAdapter()
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@UIActivity,"No Result",Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun SetAdapter(){
       recyclerView2.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        val uiAdapter = UINewsAdapter(articles,applicationContext)
        recyclerView2.adapter=uiAdapter
        uiAdapter.notifyDataSetChanged()
    }


    }



