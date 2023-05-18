package com.example.android.newslyrecyclerviewretrofit
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//https://newsapi.org/v2/top-headlines?country=in&apiKey=0b26dc92503949ddb2d20cd254ded462
//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=0b26dc92503949ddb2d20cd254ded462
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "0b26dc92503949ddb2d20cd254ded462"
interface NewsInterface {
    @GET("v2/top-headlines?apikey=$API_KEY")
    fun headlines(@Query("country") country: String, @Query("page") page: Int): Call<News>
}
object NewsService {
    val newsInstance: NewsInterface
    init {
//Retrofit Builder
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//Object to Call Methods
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}