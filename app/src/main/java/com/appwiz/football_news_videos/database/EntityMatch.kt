package com.appwiz.football_news_videos.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_video")
data class EntityMatch (
    @PrimaryKey
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "thumbnail")
    var thumbnail:String,
    @ColumnInfo(name = "competition")
    var competition:String,
    @ColumnInfo(name = "embed")
    var embed: String,
    @ColumnInfo(name = "time")
    var time: Long
)
