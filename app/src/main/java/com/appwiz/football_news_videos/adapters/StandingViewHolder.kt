package com.appwiz.football_news_videos.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.Standing

class StandingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindVIew(standing: Standing) {
        val rank: TextView = itemView.findViewById(R.id.rank)
        val name: TextView = itemView.findViewById(R.id.name)
        val matches: TextView = itemView.findViewById(R.id.matches)
        val win: TextView = itemView.findViewById(R.id.win)
        val draw: TextView = itemView.findViewById(R.id.draw)
        val loss: TextView = itemView.findViewById(R.id.loss)
        val diff: TextView = itemView.findViewById(R.id.diff)
        val points: TextView = itemView.findViewById(R.id.points)

        rank.text = standing.rank
        name.text = standing.name
        matches.text = standing.matches
        win.text = standing.win
        draw.text = standing.draw
        loss.text = standing.loss
        diff.text = standing.diff
        points.text = standing.points
    }
}