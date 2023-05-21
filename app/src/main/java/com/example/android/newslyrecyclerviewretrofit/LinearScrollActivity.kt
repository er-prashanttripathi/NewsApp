package com.example.android.newslyrecyclerviewretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivityLinearScrollBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinearScrollActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinearScrollBinding//Data Binding -1
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = -1
    lateinit var adapter: NewsAdapter//to access adapter for recyView

    /*   override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           binding= DataBindingUtil.setContentView(this,R.layout.activity_linear_scroll)//Data Binding -2
           adapter= NewsAdapter(this@LinearScrollActivity,articles)
           var recyclerView=binding.rcvLinearScroll
           recyclerView.adapter=adapter//Data Binding -3
           //first change to simple Recyclerviewin XML file
           recyclerView.layoutManager=LinearLayoutManager(this@LinearScrollActivity)//linear view scroll logic
           //-------------------------------------------------------------------------
           getNews()
       }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_linear_scroll)
        adapter = NewsAdapter(this@LinearScrollActivity, articles)
        val recyclerView = binding.rcvLinearScroll
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this@LinearScrollActivity)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition =
                    linearLayoutManager.findFirstVisibleItemPosition()//give position of 1st visible item
                val TotalCount = linearLayoutManager.itemCount//give total count of all news
                val firstVisibleItemView =
                    linearLayoutManager.findViewByPosition(firstVisibleItemPosition)
                val firstVisibleItemLocation = IntArray(2)
                firstVisibleItemView?.getLocationOnScreen(firstVisibleItemLocation)
                // Use firstVisibleItemLocation as needed
                if (totalResults > linearLayoutManager.itemCount && linearLayoutManager.findFirstVisibleItemPosition() >= linearLayoutManager.itemCount - 5) {
                    pageNum++
                    getNews()
                }
            }
        })

        //-------------------------------------------------------------------------
        getNews()
    }

    private fun getNews() {
        Log.d("request Page No.", "getNews: request Page No. $pageNum")
        val news = NewsService.newsInstance.headlines("in", pageNum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    totalResults = news.totalResults
                    Log.d("Api_Calling", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Api_Calling", "onFailure: ERROR IN FETCHING NEWS", t)
            }
        })
    }
}


