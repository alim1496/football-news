package com.appwiz.football_news_videos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appwiz.football_news_videos.R

class ResultFragment : Fragment() {

    lateinit var swipe:SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_video, container, false)
        swipe = view.findViewById(R.id.swipe)
        swipe.isRefreshing = false
        val value = arguments!!.getInt("result_type")
        Toast.makeText(context, "Hello $value", Toast.LENGTH_SHORT).show()
        return view
    }
}