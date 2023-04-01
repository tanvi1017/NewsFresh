package com.tanvi.newsfresh.service

import com.tanvi.newsfresh.Model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
   suspend fun getTopNews(
        @Query("country")country:String?,
        @Query("apiKey") apiKey:String?,
        @Query("pageSize") pageCount:Int,
        @Query("page") pageNumber: Int
    ) :Response<News>
    @GET("everything")
   suspend fun getNews(
        @Query("q")q:String?,
        @Query("apiKey") apiKey: String?,
        @Query("pageSize") pageCount: Int,
        @Query("page") pageNumber: Int
    ):Response<News>
}