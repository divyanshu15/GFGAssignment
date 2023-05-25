package com.divyanshu.gfgnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.divyanshu.gfgnews.network.ApiService
import com.divyanshu.gfgnews.network.RetrofitClient
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class ArticleViewModel : ViewModel() {

    private val apiService: ApiService = RetrofitClient.createService()

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> = _articles

    fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = apiService.getArticles()
                if (response.isSuccessful) {
                    val articleList = response.body()?.items ?: emptyList()
                    _articles.value = articleList.map { article ->
                        Article(
                            title = article.title,
                            imageUrl = article.thumbnail ,
                            pubDate = article.pubDate
                        )
                    }
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}