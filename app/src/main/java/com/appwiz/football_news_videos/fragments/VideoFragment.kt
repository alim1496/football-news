package com.appwiz.football_news_videos.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.activities.PlayerActivity
import com.appwiz.football_news_videos.adapters.VideoAdapter
import com.appwiz.football_news_videos.utils.NetworkState
import com.appwiz.football_news_videos.viewmodels.VideoViewModel

class VideoFragment: Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewmodel: VideoViewModel
    lateinit var adapter: VideoAdapter
    lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_video, container, false)
        recyclerView = view.findViewById(R.id.videoRV)
        swipe = view.findViewById(R.id.swipe)
        viewmodel = ViewModelProvider(this).get(VideoViewModel::class.java)
        viewmodel.loadData()
        adapter = VideoAdapter(emptyList()) { url:String ->
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("embeded_url", url)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewmodel.videos.observe(viewLifecycleOwner, Observer { adapter.setData(it) })
        viewmodel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                NetworkState.LOADING -> {
                    swipe.isRefreshing = true
                    // error.visibility = View.GONE
                }
                NetworkState.LOADED -> {
                    swipe.isRefreshing = false
                }
                else -> {
                    swipe.isRefreshing = false
                    recyclerView.visibility = View.GONE
                    // error.visibility = View.VISIBLE
                }
            }
        })
        swipe.setOnRefreshListener { viewmodel.loadData() }
        return view
    }


}