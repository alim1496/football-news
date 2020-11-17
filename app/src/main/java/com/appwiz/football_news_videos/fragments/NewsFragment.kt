package com.appwiz.football_news_videos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.adapters.NewsAdapter
import com.appwiz.football_news_videos.adapters.VideoAdapter
import com.appwiz.football_news_videos.utils.NetworkState
import com.appwiz.football_news_videos.viewmodels.NewsViewModel

class NewsFragment: Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewmodel: NewsViewModel
    lateinit var adapter: NewsAdapter
    lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_news, container, false)
        recyclerView = view.findViewById(R.id.newsRV)
        swipe = view.findViewById(R.id.swipe)
        viewmodel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewmodel.loadData(1)
        adapter = NewsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewmodel.newslist.observe(viewLifecycleOwner, Observer { adapter.reload(it) })
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
        swipe.setOnRefreshListener { viewmodel.loadData(1) }
        return view
    }
}