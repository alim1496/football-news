package com.appwiz.football_news_videos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Competition (
    @Expose
    @SerializedName("name")
    var name:String,
    @Expose
    @SerializedName("id")
    var id:Int,
    @Expose
    @SerializedName("url")
    var url:String
)

data class Match (
    @Expose
    @SerializedName("title")
    var title:String,
    @Expose
    @SerializedName("thumbnail")
    var thumbnail:String,
    @Expose
    @SerializedName("url")
    var url:String,
    @Expose
    @SerializedName("date")
    var date:String,
    @Expose
    @SerializedName("competition")
    var competition:Competition,
    @Expose
    @SerializedName("embed")
    var embed:String
)
