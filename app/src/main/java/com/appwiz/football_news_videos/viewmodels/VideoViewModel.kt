package com.appwiz.football_news_videos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appwiz.football_news_videos.connection.APIServices
import com.appwiz.football_news_videos.connection.ServiceBuilder
import com.appwiz.football_news_videos.database.EntityMatch
import com.appwiz.football_news_videos.database.RoomDB
import com.appwiz.football_news_videos.models.Match
import com.appwiz.football_news_videos.repository.MatchRepo
import com.appwiz.football_news_videos.utils.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class VideoViewModel(application: Application) : AndroidViewModel(application) {

    var videos: LiveData<List<EntityMatch>>
    var state: MutableLiveData<NetworkState> = MutableLiveData()

    private val repository: MatchRepo

    init {
        val dao = RoomDB.getDatabasenIstance(application).matchDao()
        repository = MatchRepo(dao)
        videos = repository.allMatches
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.remove(System.currentTimeMillis() - 1000*60*60*24*7)
            state.postValue(NetworkState.LOADING)
            fetchData()
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
                val entities: MutableList<EntityMatch> = ArrayList()
                for (match in matches) {
                    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                    val date = sdf.parse(match.date)!!
                    val time = date.time
                    val entityMatch = EntityMatch(
                        match.url, match.title, match.thumbnail,
                        match.competition.name, match.embed, time
                    )
                    entities.add(entityMatch)
                }
                CoroutineScope(Dispatchers.IO).launch { repository.insert(entities) }
                state.postValue(NetworkState.LOADED)
            }

        })
    }
}