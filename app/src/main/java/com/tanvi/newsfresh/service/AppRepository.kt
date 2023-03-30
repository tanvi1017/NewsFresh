package com.tanvi.newsfresh.service

class AppRepository {
    suspend fun getNews(q: String, apiKey: String, pageCount: Int, pageNumber: Int) =
        RetrofitInstance.newsApi.getNews(q, apiKey, pageCount, pageNumber)
}