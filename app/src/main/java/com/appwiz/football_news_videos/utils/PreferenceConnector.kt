package com.appwiz.football_news_videos.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceConnector {

    private val PREF_NAME = "VARIABLES"
    private val MODE = Context.MODE_PRIVATE

    fun writeString(context: Context, key: String, value: String) {
        try {
            getEditor(context).putString(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readString(context: Context, key: String, defValue: String): String? {
        return try {
            getPreferences(context)!!.getString(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace().toString()
        }
    }

    @Throws(java.lang.Exception::class)
    private fun getEditor(context: Context): SharedPreferences.Editor {
        return getPreferences(context)!!.edit()
    }

    @Throws(java.lang.Exception::class)
    fun clear(context: Context) {
        getEditor(context).clear().commit()
    }

    fun contains(context: Context, key: String): Boolean {
        return try {
            getPreferences(context)!!.contains(key)
        } catch (e: java.lang.Exception) {
            false
        }
    }

    @Throws(java.lang.Exception::class)
    private fun getPreferences(context: Context): SharedPreferences? {
        return context.getSharedPreferences(PREF_NAME, MODE)
    }
}