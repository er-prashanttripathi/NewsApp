package com.example.android.newslyrecyclerviewretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivityGridBinding
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivitySwapCorousalBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


    class SwapCorousalActivity : AppCompatActivity()  {
        private lateinit var binding: ActivitySwapCorousalBinding//Data Binding -1
        private var articles= mutableListOf<Article>()

        var pageNum=1
        var totalResults=-1
        lateinit var adapter: NewsAdapter//to access adapter for recyView
        lateinit var layoutManager: GridLayoutManager


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding= DataBindingUtil.setContentView(this,R.layout.activity_swap_corousal)//Data Binding -2
            adapter= NewsAdapter(this@SwapCorousalActivity,articles)
            var recyclerView=binding.rcvSwapCorousal
            recyclerView.adapter=adapter//Data Binding -3


            //-------------------------------------------------------------------------
            //for swap view # see recyclerview is "com.jackandphantom.carouselrecyclerview.CarouselRecyclerview"
            binding.apply {
                rcvSwapCorousal.adapter=adapter
                rcvSwapCorousal.set3DItem(true)
                rcvSwapCorousal.setAlpha(true)
                rcvSwapCorousal.setInfinite(false)
            }
            //-------------------------------------------------------------------------

            getNews()


        }

        private fun getNews() {
            val news = NewsService.newsInstance.headlines("in", pageNum)
            news.enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if (news != null) {
                        totalResults=news.totalResults
                        Log.d("Api_Calling", news.toString())
                        articles.addAll(news.articles)
                        adapter.notifyDataSetChanged()
                        /* Not worked yet
                        pageNum++
                        binding.progressBarId.visibility(VISIBLE)
                        getNews()
                        binding.progressBarId.visibility(VISIBLE)*/
                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("Api_Calling", "onFailure: ERROR IN FETCHING NEWS", t)
                }
            })
        }
    }