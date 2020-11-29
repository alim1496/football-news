package com.appwiz.football_news_videos.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.Scorers


class ScorerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(scorer:Scorers) {
        val rank:TextView = itemView.findViewById(R.id.rank)
        val name:TextView = itemView.findViewById(R.id.name)
        val country:TextView = itemView.findViewById(R.id.country)
        val club:TextView = itemView.findViewById(R.id.club)
        val goals:TextView = itemView.findViewById(R.id.goals)

        rank.text = scorer.rank
        name.text = scorer.name
        country.text = scorer.country
        club.text = scorer.club
        goals.text = scorer.goals
    }
}