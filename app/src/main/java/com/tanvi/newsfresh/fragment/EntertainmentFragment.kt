package com.tanvi.newsfresh.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.Constants
import com.tanvi.newsfresh.ItemData
import com.tanvi.newsfresh.activity.SecondActivity
import com.tanvi.newsfresh.adapter.EntertainmentAdapter
import com.tanvi.newsfresh.databinding.FragmentEntertainmentBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article
import com.tanvi.newsfresh.model.News
import com.tanvi.newsfresh.service.ApiClient
import com.tanvi.newsfresh.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


 class EntertainmentFragment : Fragment(), NewsClickInterface {
//    companion object{
//        fun callMe() = ("I'm called.")
//    }
    var rVAdapter = EntertainmentAdapter(mutableListOf(), this)
    var isScrolling:Boolean =false
    var totalItemCount:Int=0
    var visibleItemCount:Int =0
    var pastVisibleItemCount:Int =0
    var pageSize=1
    var articleList = mutableListOf<Article>()
   private lateinit var binding:FragmentEntertainmentBinding
   var news:News?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntertainmentBinding.inflate(layoutInflater)
        callApiToLoadNews()
        binding.recyclerview.adapter = rVAdapter
        setRvListeners()
        return  binding.root
    }
    val maxitemToLoad = 10
    var pageNumber = 1
    private fun callApiToLoadNews()
    {
        Log.v("pnumber", pageNumber.toString())
        val apiInterface = ApiClient.apiClient.create(ApiInterface::class.java)
        pageNumber
        val call: Call<News> = apiInterface.getNews("Entertainment", Constants.API_KEY_2,maxitemToLoad,pageNumber)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    articleList.addAll(response.body()!!.articles)
                    Log.v("pagenumm10",articleList.size.toString())
                    rVAdapter.updateNewsList(articleList)
                    binding.progressBar.visibility = View.GONE
                }
                else {
                    Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show() } }
            override fun onFailure(call: Call<News>, t: Throwable) {} }) }
//    private fun SetAdapter() {
//        binding.recyclerview.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
//        val adapter = news?.let { EntertainmentAdapter(it.articles) }
//        binding.recyclerview.adapter = adapter
//        adapter?.notifyDataSetChanged()
//    }
             private fun setRvListeners(){
             binding.recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
             override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount=recyclerView.layoutManager?.childCount?:0
                totalItemCount =recyclerView.layoutManager?.itemCount?:0
                pastVisibleItemCount=(recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                Log.v("countValue","vCount - $visibleItemCount, totalItemCount $totalItemCount, pastvis count $pastVisibleItemCount")
                if (isScrolling && visibleItemCount+pastVisibleItemCount==totalItemCount){
                    isScrolling=false
                    pageSize++
                    binding.progressBar.visibility= View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        pageNumber++
                        callApiToLoadNews()
                    }, 2000)
                }
            }
             })
        }
     override fun openNews(news: Article) {
       // MainActivity.dataNew = itemDataList[position]
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra("articleLink", news.title)
        intent.putExtra("articleLive", news.description)
        intent.putExtra("articleLivi", news.author)
        intent.putExtra("articleLinki", news.urlToImage)
        startActivity(intent)
        Toast.makeText(requireContext(),"Yaya interface is called!!!", Toast.LENGTH_LONG).show()
        Log.v("openNews","Open news is callede")
    }

     override fun onCellClickListener(position: Int):ItemData {
         TODO("Not yet implemented")
     }

 }

//getString(R.string.api_key)