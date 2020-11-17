package com.appwiz.football_news_videos.connection

import com.appwiz.football_news_videos.models.Match
import com.appwiz.football_news_videos.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("video-api/v1/")
    fun getHighlights(): Call<List<Match>>

    @GET("news/v1/getNews/")
    fun getNews(
        @Query("page") page:Int
    ): Call<List<News>>
}
