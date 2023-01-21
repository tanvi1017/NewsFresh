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
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.ScienceDataItem
import com.tanvi.newsfresh.activity.SecondActivity
import com.tanvi.newsfresh.adapter.ScienceAdapter
import com.tanvi.newsfresh.databinding.FragmentScienceBinding
import com.tanvi.newsfresh.databinding.ItemNewsBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article
import com.tanvi.newsfresh.model.News
import com.tanvi.newsfresh.service.ApiClient.apiClient
import com.tanvi.newsfresh.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScienceFragment : Fragment(),NewsClickInterface {
    var rVAdapter =ScienceAdapter(mutableListOf(),this)
    var pageSize=1
    var isScrolling:Boolean=false
    var totalItemCount:Int =0
    var visibleItemCount:Int =0
    var pastItemCount:Int=0
    var articleList = mutableListOf<Article>()
  private lateinit var binding:FragmentScienceBinding
  var news:News?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     binding=FragmentScienceBinding.inflate(layoutInflater)
        binding.recyclerview.adapter=rVAdapter
         setRVListener()
        callApiToLoadNews()
        return binding.root
    }
    val maxToLoad = 10
    var pageNumber = 1
//   private fun setAdapter(){
//        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        val adapter = news?.let { ScienceAdapter(it.articles) }
//        binding.recyclerview.adapter = adapter
//       adapter?.notifyDataSetChanged()
//    }
    private fun callApiToLoadNews(){
    Log.v("pageno",pageNumber.toString())
        val apiInterface = apiClient.create(ApiInterface::class.java)
        val call:Call<News> = apiInterface.getNews("Science",Constants.API_KEY_2,maxToLoad,pageNumber)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    articleList.addAll(response.body()!!.articles)
                    Log.v("countNo10",articleList.toString())
                    rVAdapter.updateNewsList(articleList)
                    binding.progressBar.visibility =View.GONE
                   // news = response.body()
                    //setAdapter()
                } else {
                    //Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show()
                }
                    }
            override fun onFailure(call: Call<News>, t: Throwable) {}
        })
    }
    private fun setRVListener(){
        binding.recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                   isScrolling=true
                }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.layoutManager?.itemCount?:0
                totalItemCount=recyclerView.layoutManager?.childCount?:0
                pastItemCount=(recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
              Log.v("count value","visiblecount-$visibleItemCount,totalItemCount-$totalItemCount,pastItemCount-$pastItemCount")
          if ( isScrolling && visibleItemCount+pastItemCount==totalItemCount){
              isScrolling= false
              pageSize++
              binding.progressBar.visibility=View.VISIBLE
              Handler(Looper.getMainLooper()).postDelayed({
                 callApiToLoadNews()
                  pageNumber++
             }, 2000)
          }

            }

        })
    }

  override fun openNews(newsModel: Article) {
      val intent = Intent(context,SecondActivity::class.java)
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
