package com.appwiz.football_news_videos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appwiz.football_news_videos.models.Standing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ResultViewModel(application: Application): AndroidViewModel(application) {

    var standingData: MutableLiveData<List<Standing>> = MutableLiveData()

    fun loadData(league:String) {
        CoroutineScope(Dispatchers.IO).launch {
            fetchData(league)
        }
    }

    private fun fetchData(league:String) {
        val connection = URL("https://alim1496.github.io/standings/${league}/2019-2020.json")
            .openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        val obj = JSONObject(data)
        val sites = obj.getJSONArray("standing")
        val standings:MutableList<Standing> = ArrayList()

        for (i in 0 until sites.length()) {
            val site = sites.getJSONObject(i)
            val standing = Standing(site.getString("rank"), site.getString("name"),
                site.getString("matches"), site.getString("win"), site.getString("draw"),
                site.getString("loss"), site.getString("diff"), site.getString("points"))
            standings.add(standing)
        }

        standingData.postValue(standings)

    }
}