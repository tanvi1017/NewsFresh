package com.tanvi.newsfresh

import android.app.SearchManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,
    FragmentTopNews.OnFragmentInteractionListener, View.OnClickListener,
    FragmentSearchNews.OnFragmentInteractionListener {
    var layoutManager: RecyclerView.LayoutManager? = null
    var errorLayout: RelativeLayout? = null
    lateinit var tabs: TabLayout
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        tabs = findViewById(R.id.tabs)
        // btnRetry = findViewById(R.id.btnRetry);
        errorLayout = findViewById(R.id.error_layout)
        viewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager)
        tabs.setupWithViewPager(viewPager)
        //        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
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
        adapter.addFragment(FragmentTopNews.newInstance("",true), "Top")
        adapter.addFragment(FragmentTopNews.newInstance("Sports"), "Sports")
        adapter.addFragment(FragmentTopNews.newInstance("Technology"), "Technology")
        adapter.addFragment(FragmentTopNews.newInstance("Healthcare"), "Healthcare")
        adapter.addFragment(FragmentTopNews.newInstance("Entertainment"), "Entertainment")
        adapter.addFragment(FragmentTopNews.newInstance("Science"), "Science")
        viewPager!!.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
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
    fun hideLayout() {
        tabs!!.visibility = View.GONE
        viewPager!!.visibility = View.GONE
    }

    fun showLayout() {
        tabs!!.visibility = View.VISIBLE
        viewPager!!.visibility = View.VISIBLE
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