package com.appwiz.football_news_videos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appwiz.football_news_videos.models.ResultItem
import com.appwiz.football_news_videos.models.Scorers
import com.appwiz.football_news_videos.models.Standing
import com.appwiz.football_news_videos.utils.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ResultViewModel(application: Application): AndroidViewModel(application) {

    var standingData: MutableLiveData<MutableList<ResultItem>> = MutableLiveData()
    var networkState: MutableLiveData<NetworkState> = MutableLiveData()

    fun loadData(type:Int, league:String) {
        CoroutineScope(Dispatchers.IO).launch {
            networkState.postValue(NetworkState.LOADING)
            when (type) {
                1 -> fetchData(league)
                2 -> fetchScorers(league)
                3 -> fetchAssists(league)
            }
        }
    }

    private fun fetchData(league:String) {
        val connection = URL("https://alim1496.github.io/standings/${league}/2019-2020.json")
            .openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        val obj = JSONObject(data)
        val sites = obj.getJSONArray("standing")
        val standings:MutableList<ResultItem> = ArrayList()

        for (i in 0 until sites.length()) {
            val site = sites.getJSONObject(i)
            val standing = Standing(site.getString("rank"), site.getString("name"),
                site.getString("matches"), site.getString("win"), site.getString("draw"),
                site.getString("loss"), site.getString("diff"), site.getString("points"))
            standings.add(standing)
        }

        networkState.postValue(NetworkState.LOADED)
        standingData.postValue(standings)

    }

    private fun fetchScorers(league:String) {
        val connection = URL("https://alim1496.github.io/scorers/${league}/2019-2020.json")
            .openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        val obj = JSONObject(data)
        val sites = obj.getJSONArray("top_scorers")
        val topScorers:MutableList<ResultItem> = ArrayList()

        for (i in 0 until sites.length()) {
            val site = sites.getJSONObject(i)
            val scorer = Scorers(site.getString("rank"), site.getString("name"),
                site.getString("country"), site.getString("club"), site.getString("goals"))
            topScorers.add(scorer)
        }

        networkState.postValue(NetworkState.LOADED)
        standingData.postValue(topScorers)

    }

    private fun fetchAssists(league:String) {}
}