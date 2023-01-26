package com.tanvi.newsfresh

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.tanvi.newsfresh.Model.Article
import com.tanvi.newsfresh.Model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentTopNews : Fragment() {
    var ty_error: TextView? = null
    var iv_error: ImageView? = null
    var recyclerViewTopNews: RecyclerView? = null
    var frameLayoutTopNews: FrameLayout? = null
    var mListener: OnFragmentInteractionListener? = null
    var recyclerView: RecyclerView? = null
    var rvAdapter: RvAdapter? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var errorLayout: RelativeLayout? = null
    var btnRetry: Button? = null
    lateinit var progressBar:ProgressBar
    private var mShimmerViewContainer: ShimmerFrameLayout? = null
    private var articles: List<Article> = ArrayList()
    var pageSize =1
    var page =1
    var isScrolling:Boolean=false
    var visibleItemCount :Int =0
    var pastVisibleItemCount:Int =0
    var totalItemCount:Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private var newsKey: String=""
    private var isTopNews= false

    companion object {
        val NEWS_KEY = "newsKey"
        val IS_TOP_NEWS = "isTopNews"

        fun newInstance(newsKey: String = "", isTopNews: Boolean = false): FragmentTopNews {
            val fragment = FragmentTopNews()
            val args = Bundle()
            args.putString(NEWS_KEY, newsKey)
            args.putBoolean(IS_TOP_NEWS, isTopNews)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val view = inflater.inflate(R.layout.fragment_top_news, container, false)
        if (arguments != null) {
            newsKey = arguments?.getString(NEWS_KEY).toString();
            isTopNews =arguments?.getBoolean(IS_TOP_NEWS)?:false
        }

        frameLayoutTopNews = view.findViewById(R.id.topNewsLayout)
        iv_error = view.findViewById(R.id.iv_error)
        progressBar =view.findViewById(R.id.progressBar)

        ty_error = view.findViewById(R.id.tv_error)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container)
        //  btnRetry=view.findViewById(R.id.btnRetry);
        errorLayout = view.findViewById(R.id.error_layout)
        recyclerView = view.findViewById(R.id.recylerviewTopNews)
        layoutManager = LinearLayoutManager(activity)
        recyclerView?.setLayoutManager(layoutManager)
        recyclerView?.setItemAnimator(DefaultItemAnimator())
        // recyclerView.setAdapter(rvAdapter);
        recyclerView?.setNestedScrollingEnabled(false)
        ViewCompat.setNestedScrollingEnabled(recyclerView!!, false)
        recyclerView?.setItemAnimator(DefaultItemAnimator())
        mShimmerViewContainer?.startShimmer()
        //  recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        //recyclerView);
        checkNetwork()
        swipeRefreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            checkNetwork()
            loadJSON(pageSize)
            swipeRefreshLayout?.setRefreshing(false)
        })
        loadJSON(pageSize)
        setRvListeners()
        return view
    }

    fun loadJSON( page:Int) {
        val apiInterface = ApiClient.apiClient.create(ApiInterface::class.java)
        val country = Utils.country
        val pageSize = 10

        val call: Call<News> = if(isTopNews){
            apiInterface.getTopNews("in", Constant.API_KEY ,pageSize)
        }else{
            apiInterface.getNews(newsKey,Constant.API_KEY,page)
        }

        //
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful && response.body()!!.articles != null) {
                    articles = response.body()!!.articles
                    rvAdapter = activity?.let { RvAdapter(articles, it) }
                    recyclerView!!.adapter = rvAdapter
                    rvAdapter!!.notifyDataSetChanged()
                    mShimmerViewContainer!!.stopShimmer()
                    mShimmerViewContainer!!.visibility = View.GONE
                } else {
                    Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {}
        })
    }
    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri?)
    }
    fun checkNetwork() {
        if (isNetworkAvailable) {
            ty_error!!.visibility = View.GONE
            iv_error!!.visibility = View.GONE
            errorLayout!!.visibility = View.GONE
            mShimmerViewContainer!!.visibility = View.VISIBLE
            recyclerView!!.visibility = View.VISIBLE
            // loadJSON();
            //      Toast.makeText(this, "connecte4d", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "no conn", Toast.LENGTH_SHORT).show()
            recyclerView!!.visibility = View.GONE
            ty_error!!.visibility = View.VISIBLE
            iv_error!!.visibility = View.VISIBLE
            mShimmerViewContainer!!.visibility = View.GONE
            errorLayout!!.visibility = View.VISIBLE
        }
    }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
                .isConnected
        }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
    private fun setRvListeners() {
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.layoutManager?.childCount ?:0
                totalItemCount= recyclerView.layoutManager?.itemCount ?:0
                pastVisibleItemCount=(recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                Log.v("countValue","vCount - $visibleItemCount, totalItemCount $totalItemCount, pastvis count $pastVisibleItemCount")
                if (isScrolling && visibleItemCount+pastVisibleItemCount==totalItemCount)
                {
                    isScrolling = false
                progressBar.visibility= View.VISIBLE
                    page++
                    Handler(Looper.getMainLooper()).postDelayed({
                        loadJSON(pageSize)
                    }, 2000)
                }
            }

        })
    }
}
















