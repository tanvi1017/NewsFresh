package com.tanvi.newsfresh.Activity

import android.app.SearchManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.Adapters.TabAdapter
import com.tanvi.newsfresh.Fragments.FragmentSearchNews
import com.tanvi.newsfresh.Fragments.FragmentTopNews
import com.tanvi.newsfresh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,
    FragmentTopNews.OnFragmentInteractionListener, View.OnClickListener,
    FragmentSearchNews.OnFragmentInteractionListener {
    var layoutManager: RecyclerView.LayoutManager? = null
    var errorLayout: RelativeLayout? = null
   // lateinit var tabs: TabLayout
    var viewPager: ViewPager? = null
    private lateinit var  binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainAppbar.toolbar)

       // val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(binding.toolbar)
       // tabs = findViewById(R.id.tabs)
        // btnRetry = findViewById(R.id.btnRetry);
       // errorLayout = findViewById(R.id.e_layout)
       // viewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager)
      //binding.tabs.setupWithViewPager(viewPager)
        binding.mainAppbar.tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as androidx.appcompat.widget.SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Search News here..."
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    //   hideLayout();
                    Log.v("strText1",query)
                    LoadFragSearch(query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                // hideLayout();
                //LoadFragSearch();
                return false
            }
        })
        return true
    }
    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(FragmentTopNews.newInstance("", true), "Top")
        adapter.addFragment(FragmentTopNews.newInstance("Sports"), "Sports")
        adapter.addFragment(FragmentTopNews.newInstance("Technology"), "Technology")
        adapter.addFragment(FragmentTopNews.newInstance("Healthcare"), "Healthcare")
        adapter.addFragment(FragmentTopNews.newInstance("Entertainment"), "Entertainment")
        adapter.addFragment(FragmentTopNews.newInstance("Science"), "Science")
        viewPager!!.adapter = adapter
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true
    }
    override fun onClick(v: View) {}
    private fun hideLayout() {
     // binding.tabs.visibility = View.GONE
      // viewPager!!.visibility = View.GONE
        binding.mainAppbar.tabs.visibility = View.GONE
        binding.viewPager.visibility =View.GONE
    }
    fun showLayout() {
      //binding.tabs.visibility = View.VISIBLE
      // viewPager!!.visibility = View.VISIBLE
        binding.mainAppbar.tabs.visibility =View.GONE
        binding.viewPager.visibility =View.GONE
    }
    fun LoadFragSearch(query: String?) {
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
    override fun onFragmentInteraction(uri: Uri?) {
    }
}