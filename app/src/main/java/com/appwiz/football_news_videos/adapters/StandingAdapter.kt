package com.appwiz.football_news_videos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.ResultItem
import com.appwiz.football_news_videos.models.Scorers
import com.appwiz.football_news_videos.models.Standing

class StandingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var resultList:MutableList<ResultItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ResultItem.TYPE_STANDING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.standing_item, parent, false)
                return StandingViewHolder(view)
            }
            ResultItem.TYPE_SCORERS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.scorer_item, parent, false)
                return ScorerViewHolder(view)
            }
        }
        throw IllegalArgumentException("unknown view type \$viewType")
    }

    override fun getItemViewType(position: Int): Int = resultList.get(position).typeResultItem

    override fun getItemCount(): Int = resultList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ResultItem.TYPE_STANDING -> {
                val standing = resultList.get(position) as Standing
                (holder as StandingViewHolder).bindVIew(standing)
            }
            ResultItem.TYPE_SCORERS -> {
                val scorer = resultList.get(position) as Scorers
                (holder as ScorerViewHolder).bindView(scorer)
            }
        }
    }

    fun appendData(data:MutableList<ResultItem>) {
        resultList.clear()
        resultList.addAll(data)
        notifyDataSetChanged()
    }

}