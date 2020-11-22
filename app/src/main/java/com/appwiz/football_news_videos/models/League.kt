package com.appwiz.football_news_videos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class League (
    @Expose
    @SerializedName("name")
    var name:String,
    @Expose
    @SerializedName("logo")
    var logo:String,
    @Expose
    @SerializedName("season")
    var season:String
)