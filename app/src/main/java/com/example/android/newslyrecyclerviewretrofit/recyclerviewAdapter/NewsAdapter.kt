package com.example.android.newslyrecyclerviewretrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.newslyrecyclerviewretrofit.databinding.ItemLayoutBinding
//---------------Using Data Binding------------------
//View HOlder
class NewsAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var newsImage = binding.newsImage
        var newsTitle = binding.newsTitle
        var newsDescription = binding.newsDescription
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val binding=ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)

       /* val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )*/
    }
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.newsTitle.text = article.title
        holder.binding.newsDescription.text = article.description
// load image using glide
        Glide
            .with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.try_later)
            .into(holder.newsImage)
//clicklistener on each row of recyclerview
        holder.itemView.setOnClickListener {
            Toast.makeText(context, article.title, Toast.LENGTH_SHORT).show()
            val intent=Intent(context,DetailInfoActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return articles.size
    }
}