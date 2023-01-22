package com.tanvi.newsfresh.Model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)