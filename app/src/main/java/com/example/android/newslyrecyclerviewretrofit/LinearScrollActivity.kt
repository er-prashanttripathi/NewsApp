package com.example.android.newslyrecyclerviewretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivityLinearScrollBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinearScrollActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinearScrollBinding//Data Binding -1
    private var articles= mutableListOf<Article>()
    var pageNum=2
    var totalResults=-1
    lateinit var adapter: NewsAdapter//to access adapter for recyView
    lateinit var layoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_linear_scroll)//Data Binding -2
        adapter= NewsAdapter(this@LinearScrollActivity,articles)
        var recyclerView=binding.rcvLinearScroll
        recyclerView.adapter=adapter//Data Binding -3
        //first change to simple Recyclerviewin XML file
        recyclerView.layoutManager=LinearLayoutManager(this@LinearScrollActivity)//linear view scroll logic
        //-------------------------------------------------------------------------
        getNews()
    }
    private fun getNews() {
        val news = NewsService.newsInstance.headlines("us", pageNum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    totalResults=news.totalResults
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


