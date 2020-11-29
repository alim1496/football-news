package com.appwiz.football_news_videos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.ResultItem
import com.appwiz.football_news_videos.models.Standing

class StandingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var resultList:MutableList<ResultItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ResultItem.TYPE_RESULT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.standing_item, parent, false)
                return StandingViewHolder(view)
            }
        }
        throw IllegalArgumentException("unknown view type \$viewType")
    }

    override fun getItemViewType(position: Int): Int = resultList.get(position).typeResultItem

    override fun getItemCount(): Int = resultList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val standing = resultList.get(position) as Standing
        (holder as StandingViewHolder).bindVIew(standing)
    }

    fun appendData(data:MutableList<ResultItem>) {
        resultList.clear()
        resultList.addAll(data)
        notifyDataSetChanged()
    }

}