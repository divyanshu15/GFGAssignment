package com.divyanshu.gfgnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ArticleAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the articleViewModel property using ViewModelProvider
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        articleViewModel.articles.observe(this, { articles ->
            adapter.articles = articles
            swipeRefreshLayout.isRefreshing = false
            adapter.notifyDataSetChanged()
        })

        articleViewModel.fetchArticles()

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        setupSwipeRefreshLayout()
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            articleViewModel.fetchArticles()
        }
    }
}
