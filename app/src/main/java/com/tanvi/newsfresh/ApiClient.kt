package com.tanvi.newsfresh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 object ApiClient {
    const val BASE_URL = "https://newsapi.org/v2/"
    private var retrofit: Retrofit? = null

    @JvmStatic
    val apiClient: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
}

