package com.tanvi.newsfresh.Activity

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tanvi.newsfresh.R

class DetailActivity:AppCompatActivity() {
    lateinit var webView:WebView
    var progressBar:ProgressBar?=null
    var url:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
       // setContentView(toolbar)
        toolbar.title = "NewsFresh"
        val intent = intent
        url = intent.extras!!.getString("news detail")

        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar!!.max = 100
        progressBar!!.progress = 1
        webView = findViewById(R.id.webView)

//        val webSettings = webView?.getSettings()
//        webSettings?.javaScriptEnabled = true
//        webView?.loadUrl(url!!)
//        webView?.setWebChromeClient(object : WebChromeClient() {
//            override fun onProgressChanged(view: WebView, progress: Int) {
//                progressBar!!.progress = progress
//            }
//        })
//        webView?.setWebViewClient(object : WebViewClient() {
//            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//                super.onPageStarted(view, url, favicon)
//                progressBar!!.visibility = View.VISIBLE
//            }
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                view.loadUrl(url)
//                return true
//            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//                progressBar!!.visibility = View.GONE
//            }
//        })
        webView.webViewClient = WebViewClient()

        // this will load the url of the website

        url?.let { webView.loadUrl(it) }

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)
    }

     // if you press Back button this code will work

}