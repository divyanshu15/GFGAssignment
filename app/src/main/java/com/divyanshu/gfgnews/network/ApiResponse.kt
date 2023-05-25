package com.divyanshu.gfgnews.network

data class ApiResponse(
    val status: String,
    val items: List<NewsItem>
)

data class NewsItem(
    val title: String,
    val pubDate: String,
    val link: String,
    val thumbnail : String,

)