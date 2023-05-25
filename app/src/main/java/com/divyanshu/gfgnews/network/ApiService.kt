package com.divyanshu.gfgnews.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml")
    suspend fun getArticles(): Response<ApiResponse>
}