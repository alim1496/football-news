package com.appwiz.football_news_videos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Scorers(
    _rank:String,
    _name:String,
    _country:String,
    _club:String,
    _goals:String
) : ResultItem {
    @Expose
    @SerializedName("rank")
    var rank: String = _rank
    @Expose
    @SerializedName("name")
    var name: String = _name
    @Expose
    @SerializedName("country")
    var country: String = _country
    @Expose
    @SerializedName("club")
    var club: String = _club
    @Expose
    @SerializedName("goals")
    var goals: String = _goals

    override val typeResultItem: Int
        get() = ResultItem.TYPE_SCORERS
}