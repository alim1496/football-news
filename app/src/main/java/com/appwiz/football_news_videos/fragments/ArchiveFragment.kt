package com.appwiz.football_news_videos.fragments

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.adapters.LeagueAdapter
import com.appwiz.football_news_videos.models.League
import org.json.JSONObject
import java.io.InputStream

class ArchiveFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_news, container, false)
        recyclerView = view.findViewById(R.id.newsRV)

        val listString = context?.let { getJsonFile("leagues.json", it) }
        val siteObj = JSONObject(listString!!)
        val sites = siteObj.getJSONArray("leagues")
        val websites:MutableList<League> = ArrayList()
        for (i in 0 until sites.length()) {
            val site = sites.getJSONObject(i)
            val news = League(site.getString("name"), site.getString("logo"),
                site.getString("season"))
            websites.add(news)
        }

        adapter = LeagueAdapter(websites)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        return view
    }

    fun getJsonFile(filename: String?, context: Context): String? {
        val manager: AssetManager = context.assets
        val file: InputStream = manager.open(filename!!)
        val formArray = ByteArray(file.available())
        file.read(formArray)
        file.close()
        return String(formArray)
    }
}