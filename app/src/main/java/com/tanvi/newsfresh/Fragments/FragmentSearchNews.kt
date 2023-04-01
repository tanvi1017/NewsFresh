package com.tanvi.newsfresh.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.Adapters.RvAdapter
import com.tanvi.newsfresh.Model.Article
//import com.tanvi.newsfresh.NewsResponse.Companion.n1
import com.tanvi.newsfresh.databinding.FragmentSearchNewsBinding
import com.tanvi.newsfresh.service.AppRepository
import com.tanvi.newsfresh.util.Resources
import com.tanvi.newsfresh.util.ViewModelProviderFactory
import com.tanvi.newsfresh.viewmodel.NewsViewModel





class FragmentSearchNews : Fragment() {
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentSearchNewsBinding
    var mListener: OnFragmentInteractionListener? = null
    var rvAdapter: RvAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var btnRetry: Button? = null
    var pageCount = 50
   // private var mShimmerViewContainer: ShimmerFrameLayout? = null
    private var articles: MutableList<Article> = mutableListOf()

    override fun onCreateView(
           inflater: LayoutInflater, container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {
        binding = FragmentSearchNewsBinding.inflate(layoutInflater)

        layoutManager = LinearLayoutManager(requireActivity())
        binding.recylerviewTopNews.layoutManager = layoutManager
        binding.recylerviewTopNews.itemAnimator = DefaultItemAnimator()
        binding.shimmerViewContainer.startShimmer()
        checkNetwork()
        setupViewModel()
        binding.swipeRefresh.setOnRefreshListener {
            checkNetwork()
            binding.swipeRefresh.isRefreshing = false
        }
        return binding.root
    }


       interface OnFragmentInteractionListener {
           fun onFragmentInteraction(uri: Uri?)
       }

       private fun checkNetwork() {
           if (isNetworkAvailable) {
               binding.eLayout.tvError.visibility = View.GONE
               binding.eLayout.ivError.visibility = View.GONE
             binding.shimmerViewContainer .visibility = View.VISIBLE
               binding.recylerviewTopNews.visibility = View.VISIBLE
           } else {
               Toast.makeText(activity, "no connection", Toast.LENGTH_SHORT).show()
             binding.swipeRefresh.visibility = View.GONE
               binding.eLayout.tvError.visibility = View.VISIBLE
               binding.eLayout.ivError.visibility = View.VISIBLE
              binding.shimmerViewContainer.visibility = View.GONE
           }
       }

       private val isNetworkAvailable: Boolean
           get() {
               val connectivityManager =
                   activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
               return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
           }

       override fun onAttach(context: Context) {
           super.onAttach(context)
           mListener = if (context is OnFragmentInteractionListener) {
               context
           } else {
               throw RuntimeException(
                   "$context must implement OnFragmentInteractionListener"
               )
           }
       }
       override fun onDetach() {
           super.onDetach()
           mListener = null
       }
    private fun setupViewModel(){
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        getNews()
    }
    private fun getNews(){
  viewModel.newsData.observe(viewLifecycleOwner) { response ->
      when (response) {
          is Resources.Success -> {
              response.data?.let {
                  articles.addAll(it.articles)
              }
              rvAdapter =  RvAdapter(articles, requireContext())
              binding.recylerviewTopNews.adapter = rvAdapter
              rvAdapter?.notifyDataSetChanged()
              binding.shimmerViewContainer.visibility = View.GONE

          }
          is Resources.Error -> {
              Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show()
              binding.shimmerViewContainer.visibility = View.GONE
          }
          is Resources.Loading -> {
              binding.shimmerViewContainer.showShimmer(true)
              binding.shimmerViewContainer.visibility = View.VISIBLE

          }
      }
  }
    }

   }

