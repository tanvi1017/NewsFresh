package com.tanvi.newsfresh.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tanvi.newsfresh.fragment.*

private const val NUM_TABS = 5

    class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return NUM_TABS
        }

        override fun createFragment(position: Int): Fragment {
            when (position) { //-1
                0 -> return   TechnologyFragment()
                2 -> return ScienceFragment()
                3-> return SportFragment()
                1-> return HealthCareFragment()
                4->return EntertainmentFragment()
            }
            return ScienceFragment()
        }
    }
