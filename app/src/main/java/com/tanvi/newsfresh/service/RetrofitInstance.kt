package com.tanvi.newsfresh.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofitCall by lazy {
            Retrofit.Builder().baseUrl(BASE_URL) .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val newsApi by lazy {
            retrofitCall.create(ApiInterface::class.java)
        }

        private const val BASE_URL = "https://newsapi.org/v2/"

    }

}




