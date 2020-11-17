package com.appwiz.football_news_videos.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.appwiz.football_news_videos.database.EntityMatch
import com.appwiz.football_news_videos.database.MatchDao

class MatchRepo(private val dao: MatchDao) {

    val allMatches: LiveData<List<EntityMatch>> = dao.showMatches()

    suspend fun insert(chars:List<EntityMatch>) {
        dao.addMatches(chars)
    }

    suspend fun check() = dao.checkEmptyMatches()

    suspend fun remove(time:Long) {
        dao.removeMatches(time)
    }
}