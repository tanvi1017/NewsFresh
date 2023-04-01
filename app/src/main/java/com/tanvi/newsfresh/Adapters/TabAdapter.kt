package com.tanvi.newsfresh.Adapters

import android.icu.text.CaseMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter(fm:FragmentManager?):FragmentPagerAdapter(fm!!) {
  private val mFragment:MutableList<Fragment> = ArrayList()
    private val TitleList:MutableList<String> =ArrayList()

    override fun getCount(): Int {
        return mFragment.size
    }
    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return TitleList[position]
    }
    fun addFragment(fragment: Fragment, title: String) {
        mFragment.add(fragment)
        TitleList.add(title)
    }

}