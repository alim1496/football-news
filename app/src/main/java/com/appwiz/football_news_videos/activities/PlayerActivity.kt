package com.appwiz.football_news_videos.activities

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.appwiz.football_news_videos.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader


class PlayerActivity : AppCompatActivity() {

    lateinit var webview : WebView
    lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_video_player)

        val embed = intent.getStringExtra("embeded_url")
        val words = embed.split(" ")
        for (word in words) {
            if (word.startsWith("src=")) {
                url = word.substring(5, word.length - 1)
            }
        }

        webview = findViewById(R.id.web_video)
        webview.settings.javaScriptEnabled = true
        webview.settings.useWideViewPort = true

        webview.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                val css = ".EmbedCodeWrapper,.WidgetFt{display:none;}"
                val js = "var style = document.createElement('style'); style.innerHTML = '$css'; document.head.appendChild(style);"
                webview.evaluateJavascript(js,null)
                super.onPageFinished(view, url)
            }
        }

        webview.loadUrl(url)
    }
}