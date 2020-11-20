package com.appwiz.football_news_videos.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appwiz.football_news_videos.connection.APIServices
import com.appwiz.football_news_videos.connection.ServiceBuilder
import com.appwiz.football_news_videos.models.Match
import com.appwiz.football_news_videos.utils.NetworkState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VideoViewModel(application: Application) : AndroidViewModel(application) {

    var videos: MutableLiveData<List<Match>> = MutableLiveData()
    var state: MutableLiveData<NetworkState> = MutableLiveData()
    private val sharedPreference = application.getSharedPreferences("MyPreference", Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()
    private val gson = Gson()
    private val TIME_DIFF = 30 * 60 * 1000

    fun loadData() {
        state.postValue(NetworkState.LOADING)
        val listHighlights = sharedPreference.getString("match_highlights", "")!!
        val lastTime = sharedPreference.getLong("last_highlights_time", 0)
        if (listHighlights.isEmpty() || (System.currentTimeMillis() - lastTime >= TIME_DIFF)) {
            fetchData()
        } else {
            val type = object : TypeToken<List<Match>>() {}.type
            state.postValue(NetworkState.LOADED)
            videos.postValue(gson.fromJson(listHighlights, type))
        }
    }

    private fun fetchData() {
        val call = ServiceBuilder.buildService(APIServices::class.java).getHighlights()
        call.enqueue(object : Callback<List<Match>> {
            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                state.postValue(NetworkState.ERROR)
            }
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                val matches = response.body()!!
                videos.postValue(matches)
                state.postValue(NetworkState.LOADED)
                editor.putString("match_highlights", gson.toJson(matches))
                editor.putLong("last_highlights_time", System.currentTimeMillis())
                editor.apply()
            }
        })
    }
}