package com.appwiz.football_news_videos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE = "football_db"

@Database(entities = [EntityMatch::class],
    version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun matchDao() : MatchDao

    companion object {

        @Volatile private var INSTANCE: RoomDB? = null

        fun getDatabasenIstance(mContext: Context): RoomDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabaseInstance(mContext).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, RoomDB::class.java, DATABASE)
                .fallbackToDestructiveMigration()
                .build()

    }
}
