package com.example.android.newslyrecyclerviewretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivityDetailInfoBinding
import java.util.Objects

class DetailInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_info)
        binding.apply {
            val url = intent.getStringExtra("URL")
            if (url != null) {
            /*    detailWebView.settings.javaScriptEnabled=true
                detailWebView.settings.userAgentString=*/
                binding.detailWebView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        progressBar.visibility=View.GONE
                        detailWebView.visibility=View.VISIBLE
                    }
                }
                detailWebView.loadUrl(url)
            }

        }
    }
}