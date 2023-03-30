package com.tanvi.newsfresh.service

import com.bumptech.glide.load.engine.Resource
import com.tanvi.newsfresh.Model.News
import com.tanvi.newsfresh.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getTopNews(
        @Query("country")country:String?,
        @Query("apiKey") apiKey:String?,
        @Query("pageSize") pageCount:Int,
        @Query("page") pageNumber: Int
    ) :Response<NewsResponse>
    @GET("everything")
    fun getNews(
        @Query("q")q:String?,
        @Query("apiKey") apiKey: String?,
        @Query("pageSize") pageCount: Int,
        @Query("page") pageNumber: Int
    ):Response<NewsResponse>
}