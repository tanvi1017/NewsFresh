package com.tanvi.newsfresh.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.tanvi.newsfresh.*
import com.tanvi.newsfresh.Adapters.RvAdapter
import com.tanvi.newsfresh.Model.Article
import com.tanvi.newsfresh.databinding.FragmentSearchNewsBinding
import com.tanvi.newsfresh.databinding.FragmentTopNewsBinding
import com.tanvi.newsfresh.service.AppRepository
import com.tanvi.newsfresh.util.Resources
import com.tanvi.newsfresh.util.ViewModelProviderFactory
import com.tanvi.newsfresh.viewmodel.NewsViewModel


class FragmentTopNews : Fragment() {
    lateinit var binding: FragmentTopNewsBinding
    var mListener: OnFragmentInteractionListener? = null
    var rvAdapter: RvAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var viewModel: NewsViewModel
    private var articles: MutableList<Article> = ArrayList()
    var pageNumber = 1
    var pageCount = 10
    var isScrolling: Boolean = false
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var totalItemCount: Int = 0
    private var newsKey: String = ""
    private var isTopNews = false

    companion object {
        const val NEWS_KEY = "newsKey"
        const val IS_TOP_NEWS = "isTopNews"

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopNewsBinding.inflate(layoutInflater)

        if (arguments != null) {
            newsKey = arguments?.getString(NEWS_KEY).toString();
            isTopNews = arguments?.getBoolean(IS_TOP_NEWS) ?: false
        }
        setupViewModel()
        layoutManager = LinearLayoutManager(activity)
        binding.recylerviewTopNews.layoutManager = layoutManager
        binding.recylerviewTopNews.itemAnimator = DefaultItemAnimator()
        ViewCompat.setNestedScrollingEnabled(binding.recylerviewTopNews, false)
        binding.recylerviewTopNews.itemAnimator = DefaultItemAnimator()
        binding.shimmerViewContainer.startShimmer()

        checkNetwork()

        binding.swipeRefresh.setOnRefreshListener {
            checkNetwork()
            setRvAdapter()
            binding.swipeRefresh.isRefreshing = false
        }
        setRvAdapter()
        return binding.root
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        getNews()
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri?)
    }

    private fun checkNetwork() {
        if (isNetworkAvailable) {
            binding.includeErrorLayout.apply {
                tvError.visibility = View.GONE
                ivError.visibility = View.GONE
                binding.shimmerViewContainer.visibility = View.VISIBLE
                binding.recylerviewTopNews.visibility = View.VISIBLE
            }
        } else {
            Toast.makeText(activity, "no conn", Toast.LENGTH_SHORT).show()
            binding.includeErrorLayout.apply {
                binding.recylerviewTopNews.visibility = View.GONE
                tvError.visibility = View.VISIBLE
                ivError.visibility = View.VISIBLE
                binding.shimmerViewContainer.visibility = View.GONE
            }
        }
    }

    private val isNetworkAvailable: Boolean
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
                context.toString() + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    var recyclerViewState: Parcelable? = null
    private fun setRvAdapter() {
        binding.recylerviewTopNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerViewState = layoutManager?.onSaveInstanceState(); // save recycleView state

                //totalItemCount = News.totalResults
                Log.v(
                    "cannonballed", "1called"
                )
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.layoutManager?.childCount ?: 0//3
                totalItemCount = recyclerView.layoutManager?.itemCount ?: 0   //10
                Log.v(
                    "cannonballed", "2nd called"
                )
                pastVisibleItemCount =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()//7
                Log.v(
                    "countValue",
                    "vCount - $visibleItemCount, totalItemCount $totalItemCount, pastvis count $pastVisibleItemCount"
                )
                if (isScrolling && visibleItemCount + pastVisibleItemCount == totalItemCount) {
                    isScrolling = false
                    pageNumber++
                }
            }
        })
    }

    private fun getNews() {
        viewModel.newsData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resources.Success -> {
                    response.data?.let { res ->
                        articles.addAll(res)
                    }
                    rvAdapter = activity?.let { RvAdapter(articles, it) }
                    binding.recylerviewTopNews.adapter = rvAdapter
                    rvAdapter?.notifyDataSetChanged()
                    layoutManager?.onRestoreInstanceState(recyclerViewState)
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE
                }
                is Resources.Loading -> {
                    binding.shimmerViewContainer.showShimmer(true)
                    binding.shimmerViewContainer.visibility = View.VISIBLE

                }
                is Resources.Error -> {
                    Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show()
                    binding.shimmerViewContainer.visibility = View.GONE

                }
            }

        })
    }
}
















