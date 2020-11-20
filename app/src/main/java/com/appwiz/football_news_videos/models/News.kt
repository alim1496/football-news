package com.appwiz.football_news_videos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsSite (
    @Expose
    @SerializedName("name")
    var name:String,
    @Expose
    @SerializedName("url")
    var url:String,
    @Expose
    @SerializedName("logo")
    var logo:String
)

data class Source (
    @Expose
    @SerializedName("id")
    var id:String,
    @Expose
    @SerializedName("name")
    var name:String
)

data class News (
    @Expose
    @SerializedName("title")
    var title:String,
    @Expose
    @SerializedName("urlToImage")
    var image:String,
    @Expose
    @SerializedName("source")
    var source:Source,
    @Expose
    @SerializedName("description")
    var description:String,
    @Expose
    @SerializedName("publishedAt")
    var time:String,
    @Expose
    @SerializedName("url")
    var url:String
)