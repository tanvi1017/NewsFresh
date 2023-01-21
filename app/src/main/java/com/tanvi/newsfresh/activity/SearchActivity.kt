package com.tanvi.newsfresh.activity

import android.os.Bundle
import android.view.Gravity
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tanvi.newsfresh.ItemData
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.interfaces.NewsClickInterface
import com.tanvi.newsfresh.model.Article

class SearchActivity:AppCompatActivity() {
    lateinit var search:SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        search = findViewById(R.id.search)
        intent = getIntent()
       // search.query = intent.getStringExtra("searchText")
    }
//    override fun openNews(newsModel: Article) {
//        intent.putExtra("articledescription",newsModel.description)
//        intent.putExtra("articleImg",newsModel.urlToImage)
//    }
//    override fun onCellClickListener(position: Int): ItemData {
//        TODO("Not yet implemented")
//    }
//    override fun searchViewonQuerryTextListener(text: String?) {
////        val t = Toast.makeText(this, R.string.loading, Toast.LENGTH_SHORT)
////        t.setGravity(Gravity.TOP, 0, 500)
////        t.show()
////        fetchNewsItems(text ?: "")
//    }
//    private fun fetchNewsItems(query:String =getString(R.string.default_search_text)){
//        val n = fetchNewsItems(query)
//        n.execute()
//    }


}