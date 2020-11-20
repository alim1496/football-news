package com.appwiz.football_news_videos.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.appwiz.football_news_videos.R


class NewsActivity : AppCompatActivity() {

    lateinit var webview : WebView
    lateinit var progress : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_news_details)

        webview = findViewById(R.id.web_news)
        progress = findViewById(R.id.progress)
        webview.settings.javaScriptEnabled = true
        webview.settings.useWideViewPort = true
        val url = intent.getStringExtra("site_url")

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(
                view: WebView,
                url: String,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progress.visibility = View.GONE
            }
        }

        webview.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) webview.goBack()
        else finish()
    }

}