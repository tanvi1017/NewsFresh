package com.tanvi.newsfresh.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tanvi.newsfresh.Fragments.FragmentTopNews

class TabAdapterTwo(fragmentManager :FragmentManager,lifecycle: Lifecycle, private val fragList: List<FragmentTopNews>) :FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return fragList.size
    }

    override fun createFragment(position: Int): Fragment {
        return  fragList[position]
    }

}

