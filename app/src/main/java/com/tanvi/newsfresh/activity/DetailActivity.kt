package com.tanvi.newsfresh.activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.model.Article

class DetailActivity : AppCompatActivity() {

    var webView: WebView? = null
    var progressBar: ProgressBar? = null
    var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = "NewsStar"
        val intent = intent

        url = (intent.extras!!.getSerializable("newsData") as Article).url

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar!!.max = 100
        progressBar!!.progress = 1
        webView = findViewById(R.id.webView)
        val webSettings = webView?.getSettings()
        webSettings?.javaScriptEnabled = true
        webView?.loadUrl(url!!)
        webView?.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                progressBar!!.progress = progress
            }
        })
        webView?.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
                progressBar!!.visibility = View.VISIBLE
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                progressBar!!.visibility = View.GONE
            }
        })
    }


}