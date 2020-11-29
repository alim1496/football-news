package com.appwiz.football_news_videos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.adapters.StandingAdapter
import com.appwiz.football_news_videos.models.Standing
import com.appwiz.football_news_videos.utils.NetworkState
import com.appwiz.football_news_videos.viewmodels.ResultViewModel
import com.appwiz.football_news_videos.viewmodels.VideoViewModel
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ResultFragment : Fragment() {

    lateinit var loader: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: ResultViewModel
    lateinit var standingHeader: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_results, container, false)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        loader = view.findViewById(R.id.loader)
        recyclerView = view.findViewById(R.id.videoRV)
        standingHeader = view.findViewById(R.id.section_standing)

        val type = arguments!!.getInt("result_type")
        val league = arguments!!.getString("name_league")

        if (type == 1 && league != null) {
            viewModel.loadData(type, league)
            standingHeader.visibility = View.VISIBLE
        } else if (type == 2 && league != null) {
            viewModel.loadData(type, league)
            standingHeader.visibility = View.VISIBLE
        } else {
            standingHeader.visibility = View.GONE
        }

        val adapter = StandingAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.line_divider)!!)
        recyclerView.addItemDecoration(decoration)

        viewModel.standingData.observe(viewLifecycleOwner, Observer { adapter.appendData(it) })
        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            when (it) {
                NetworkState.LOADING -> {
                    loader.visibility = View.VISIBLE
                }
                NetworkState.LOADED -> {
                    loader.visibility = View.GONE
                }
                else -> {
                    loader.visibility = View.GONE
                }
            }
        })
        return view
    }

}