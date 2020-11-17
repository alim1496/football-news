package com.appwiz.football_news_videos.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMatches(matches: List<EntityMatch>)

    @Query("select * from match_video order by time desc")
    fun showMatches(): LiveData<List<EntityMatch>>

    @Query("select count(*) from match_video")
    suspend fun checkEmptyMatches(): Int

    @Query("delete from match_video where time < :time")
    suspend fun removeMatches(time:Long)

}
