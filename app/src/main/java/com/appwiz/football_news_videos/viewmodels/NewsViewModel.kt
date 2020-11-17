package com.appwiz.football_news_videos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appwiz.football_news_videos.connection.APIServices
import com.appwiz.football_news_videos.connection.RetrofitClient
import com.appwiz.football_news_videos.database.EntityMatch
import com.appwiz.football_news_videos.database.RoomDB
import com.appwiz.football_news_videos.models.News
import com.appwiz.football_news_videos.repository.MatchRepo
import com.appwiz.football_news_videos.utils.NetworkState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL

class NewsViewModel(application: Application): AndroidViewModel(application) {
    var newslist: MutableLiveData<List<News>> = MutableLiveData()
    var state: MutableLiveData<NetworkState> = MutableLiveData()

    fun loadData(page:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            fetchData(page)
        }
    }

    private fun fetchData(page:Int) {
        val connection = URL("https://vast-forest-33763.herokuapp.com/api/news/v1/getNews/")
            .openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        val gson = GsonBuilder().create()
        val chars = gson.fromJson(data, Array<News>::class.java).toList()
        Log.e("yoyo", "list is "+chars);
        state.postValue(NetworkState.LOADED)
        newslist.postValue(chars)
//        val call = RetrofitClient.buildService(APIServices::class.java).getNews(page)
//        call.enqueue(object : Callback<List<News>> {
//            override fun onFailure(call: Call<List<News>>, t: Throwable) {
//                state.postValue(NetworkState.ERROR)
//            }
//
//            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
//                Log.e("heye", "body is " + response.errorBody().toString())
//                val allNews = response.body()!!
//                state.postValue(NetworkState.LOADED)
//                newslist.postValue(allNews)
//            }
//
//        })
    }
}