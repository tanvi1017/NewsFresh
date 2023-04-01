package com.tanvi.newsfresh.Activity

import android.app.SearchManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.Adapters.TabAdapterTwo
import com.tanvi.newsfresh.Fragments.FragmentSearchNews
import com.tanvi.newsfresh.Fragments.FragmentTopNews
import com.tanvi.newsfresh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainAppBar.toolbar)
        setupViewPager()
    }

    private fun setupViewPager() {

        val fragList = listOf(
            FragmentTopNews.newInstance(newsKey = TAB_TITLE_LIST[0]),
            FragmentTopNews.newInstance(newsKey = TAB_TITLE_LIST[1]),
            FragmentTopNews.newInstance(newsKey = TAB_TITLE_LIST[2]),
            FragmentTopNews.newInstance(newsKey = TAB_TITLE_LIST[3]),
            FragmentTopNews.newInstance(newsKey = TAB_TITLE_LIST[4]),
            FragmentTopNews.newInstance(newsKey = TAB_TITLE_LIST[5])
        )

        val adapter = TabAdapterTwo(supportFragmentManager, lifecycle, fragList)
        binding.mainAppBar.contentMain.viewpager.adapter = adapter

        TabLayoutMediator(binding.mainAppBar.tabs, binding.mainAppBar.contentMain.viewpager) { tab, position ->
            tab.text = TAB_TITLE_LIST[position]
        }.attach()
    }


    private fun hideLayout() {
        binding.mainAppBar.tabs.visibility = View.GONE
        binding.mainAppBar.contentMain.viewpager.visibility = View.GONE
    }

    fun loadFragSearch(query: String?) {
        val bundle = Bundle()
        bundle.putString("query", query)
        val frag: Fragment = FragmentSearchNews()
        frag.arguments = bundle
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        hideLayout()
        transaction.replace(R.id.mainFrame, frag, "FragmentSearchNews").addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView =
            menu.findItem(R.id.action_search).actionView as androidx.appcompat.widget.SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Search News here..."
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    loadFragSearch(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    companion object {
         val TAB_TITLE_LIST = listOf("Top","Sports", "Technology","Healthcare","Entertainment","Science")
    }
}