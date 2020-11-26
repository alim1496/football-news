package com.appwiz.football_news_videos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Standing (
    @Expose
    @SerializedName("rank")
    var rank:String,
    @Expose
    @SerializedName("name")
    var name:String,
    @Expose
    @SerializedName("matches")
    var matches:String,
    @Expose
    @SerializedName("win")
    var win:String,
    @Expose
    @SerializedName("draw")
    var draw:String,
    @Expose
    @SerializedName("loss")
    var loss:String,
    @Expose
    @SerializedName("diff")
    var diff:String,
    @Expose
    @SerializedName("points")
    var points:String
)