package com.appwiz.football_news_videos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Standing(
    _rank: String,
    _name: String,
    _matches: String,
    _win: String,
    _draw: String,
    _loss: String,
    _diff: String,
    _points: String
) : ResultItem {
    @Expose
    @SerializedName("rank")
    var rank: String = _rank
    @Expose
    @SerializedName("name")
    var name: String = _name
    @Expose
    @SerializedName("matches")
    var matches: String = _matches
    @Expose
    @SerializedName("win")
    var win: String = _win
    @Expose
    @SerializedName("draw")
    var draw: String = _draw
    @Expose
    @SerializedName("loss")
    var loss: String = _loss
    @Expose
    @SerializedName("diff")
    var diff: String = _diff
    @Expose
    @SerializedName("points")
    var points: String = _points

    override val typeResultItem: Int
        get() = ResultItem.TYPE_RESULT

}