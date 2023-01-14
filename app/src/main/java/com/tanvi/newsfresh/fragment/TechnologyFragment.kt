package com.tanvi.newsfresh.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.tanvi.newsfresh.TechnologyDataItem
import com.tanvi.newsfresh.activity.SecondActivity
import com.tanvi.newsfresh.adapter.SportAdapter
import com.tanvi.newsfresh.adapter.TechnologyAdapter
import com.tanvi.newsfresh.databinding.FragmentSportBinding
import com.tanvi.newsfresh.databinding.FragmentTechnologyBinding
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article
import com.tanvi.newsfresh.model.News
import com.tanvi.newsfresh.service.ApiClient
import com.tanvi.newsfresh.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
 class TechnologyFragment : Fragment(),NewsClickInterface {
     var rVAdapter =TechnologyAdapter(mutableListOf(),this)
//     override fun openSomethingElse(DetailedItems: Fragment) {
//         TODO("Not yet implemented")
//     }

     var isScrolling:Boolean=false
     var totalVisibleItemCount:Int=0
     var totalItemCount:Int=0
     var totalPastVisibleItemCount:Int =0
     var articleList = mutableListOf<Article>()
     var pageSize=1

   private  lateinit var binding: FragmentTechnologyBinding
   var news: News?=null
  // val TechnologyList:MutableList<TechnologyDataItem> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTechnologyBinding.inflate(layoutInflater)
      binding.recyclerview.adapter=rVAdapter
      setRVListener()
      callApiToLoadNews()
        return binding.root
    }
     val maxToLoad = 10
     var pageNumber = 1
 // private  fun setAdapter(){
//        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        //val adapter = news?.let { TechnologyAdapter(it.articles) }
//      val adapter= news?.let { TechnologyAdapter(it.articles) }
//        binding.recyclerview.adapter = adapter
//      adapter?.notifyDataSetChanged()
//    }
   private fun callApiToLoadNews(){
     pageNumber
       val apiInterface = ApiClient.apiClient.create(ApiInterface::class.java)
       val call: Call<News> = apiInterface.getNews("Technology",Constants.API_KEY_2,maxToLoad,pageNumber)
       call.enqueue(object : Callback<News> {
           override fun onResponse(call: Call<News>, response: Response<News>) {
               if (response.isSuccessful) {
                   //news = response.body()
                   //setAdapter()
                   articleList.addAll(response.body()!!.articles)
                   rVAdapter.updateNewsList(articleList)
                   binding.progressBar.visibility=View.GONE

               } else {
                   Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show()
               }
           }
           override fun onFailure(call: Call<News>, t: Throwable) {}
       })

    }
     private fun setRVListener(){
         binding.recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
             override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                 super.onScrollStateChanged(recyclerView, newState)
                 if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                     isScrolling = true
                 }
             }
             override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                 super.onScrolled(recyclerView, dx, dy)
                 totalVisibleItemCount =recyclerView.layoutManager?.childCount?:0
                 totalItemCount=recyclerView.layoutManager?.itemCount?:0
                 totalPastVisibleItemCount=(recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                 if ( isScrolling && totalVisibleItemCount+totalItemCount==totalPastVisibleItemCount)
                 {
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
     override fun openNews(newsModel: Article) {
        val intent = Intent(context,SecondActivity::class.java)
         intent.putExtra("articleLink",newsModel.title)
         intent.putExtra("articleLivw",newsModel.description)
         intent.putExtra("articleLivi",newsModel.author)
         intent.putExtra("articleLinki",newsModel.urlToImage)
         startActivity(intent)
     }

     override fun onCellClickListener(position: Int): ItemData {
         TODO("Not yet implemented")
     }


 }