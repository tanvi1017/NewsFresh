package com.tanvi.newsfresh.service

import com.tanvi.newsfresh.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getTopNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?,
        @Query("pageSize") pageCount: Int,
        @Query("page") pageNumber: Int
    ): Call<News>

    @GET("everything")
    fun getNews(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("pageSize") pageCount: Int,
        @Query("page") pageNumber: Int =1
    ): Call<News>
}
