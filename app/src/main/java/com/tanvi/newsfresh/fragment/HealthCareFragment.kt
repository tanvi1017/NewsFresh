package com.tanvi.newsfresh.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.Constants
import com.tanvi.newsfresh.HealthDataItem
import com.tanvi.newsfresh.ItemData
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.activity.SecondActivity
import com.tanvi.newsfresh.adapter.EntertainmentAdapter
import com.tanvi.newsfresh.adapter.HealthAdapter
import com.tanvi.newsfresh.adapter.ScienceAdapter
import com.tanvi.newsfresh.adapter.SportAdapter
import com.tanvi.newsfresh.databinding.FragmentHealthCareBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article
import com.tanvi.newsfresh.model.News
import com.tanvi.newsfresh.service.ApiClient
import com.tanvi.newsfresh.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

   class HealthCareFragment : Fragment(),NewsClickInterface {
    var rVAdapter = HealthAdapter(mutableListOf(),this)
    var isScrolling:Boolean =false
    var totalitemCount:Int =0
    var visibleItemCount:Int =0
    var pastVisibleItemCount:Int=0
    var pages=1
    var articleList = mutableListOf<Article>()
    private lateinit var binding: FragmentHealthCareBinding
   var news:News? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHealthCareBinding.inflate(layoutInflater)
        binding.recyclerview.adapter=rVAdapter
        callApiToLoadNews()
        setRVListeners()
        return binding.root
    }
       val maxToLoad = 10
       var pageNumber = 1

//    private fun setAdapter() {
//        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        val adapter = news?.let { HealthAdapter(it.articles) }
//            binding.recyclerview.adapter = adapter
//    }
      fun callApiToLoadNews() {
        Log.v("pageno",pageNumber.toString())
        pageNumber
        val apiInterface = ApiClient.apiClient.create(ApiInterface::class.java)
        val call: Call<News> =
            apiInterface.getNews("health", Constants.API_KEY_2, maxToLoad,pageNumber)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    Log.v("countnum10",articleList.toString())
                    articleList.addAll(response.body()!!.articles)
                    rVAdapter.updateNewsList(articleList)
                    binding.progressBar.visibility=View.GONE
                    //news = response.body()
                    //setAdapter()
                } else {
                    //Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {}
        })
    }
    private fun setRVListeners(){
        binding.recyclerview.addOnScrollListener(object:RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.layoutManager?.childCount?:0
                totalitemCount = recyclerView.layoutManager?.childCount?:0
                pastVisibleItemCount=(recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                Log.v("count value","visiblecount-$visibleItemCount,totalitem-$totalitemCount,pastcount-$pastVisibleItemCount")
                if ( isScrolling && visibleItemCount+pastVisibleItemCount==totalitemCount){
                    isScrolling=false
                    pages++
                    binding.progressBar.visibility= View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                       pageNumber++
                        callApiToLoadNews()
                   }, 2000)

                }
            }
        })
    }
       override fun openNews(newsModel: Article) {
        val intent = Intent( context,SecondActivity::class.java)
           intent.putExtra("articleLink",newsModel.title)
           intent.putExtra("articleLive",newsModel.description)
           intent.putExtra("articleLivi",newsModel.author)
           intent.putExtra("articleLinki",newsModel.urlToImage)
           startActivity(intent)

       }

       override fun onCellClickListener(position: Int): ItemData {
           TODO("Not yet implemented")
       }


   }




