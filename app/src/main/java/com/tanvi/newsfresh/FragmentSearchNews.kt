package com.tanvi.newsfresh

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.tanvi.newsfresh.ApiClient.apiClient
import com.tanvi.newsfresh.Model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSearchNews : Fragment() {
    var ty_error: TextView? = null
    var iv_error: ImageView? = null
    var recyclerViewTopNews:RecyclerView?= null
    var frameLayoutSeacrchNews: FrameLayout? = null
    var mListener: OnFragmentInteractionListener? = null
    var recyclerView: RecyclerView? = null
    var rvAdapter: RvAdapter? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var errorLayout: RelativeLayout? = null
    var btnRetry: Button? = null
    private var mShimmerViewContainer: ShimmerFrameLayout? = null
    private var articles: List<Articles> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



    }
 }
