package com.appwiz.football_news_videos.models

interface ResultItem {
    val typeResultItem: Int

    companion object {
        const val TYPE_RESULT = 0
        const val TYPE_STANDING = 1
        const val TYPE_SCORERS = 2
        const val TYPE_ASSISTS = 3
    }
}