package com.appwiz.football_news_videos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.League
import com.squareup.picasso.Picasso

class LeagueAdapter(private var leagues:List<League>, private val click: (String) -> Unit) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view:View = itemView.findViewById(R.id.name_holder)

        fun bindview(news:League) {
            val title: TextView = itemView.findViewById(R.id.title)
            val logo: ImageView = itemView.findViewById(R.id.logo)
            title.text = news.name
            when (news.logo) {
                "epl" -> Picasso.get().load(R.drawable.ic_epl).into(logo)
                "laliga" -> Picasso.get().load(R.drawable.ic_laliga).into(logo)
                "seriea" -> Picasso.get().load(R.drawable.ic_siriea).into(logo)
                "bundesliga" -> Picasso.get().load(R.drawable.ic_bliga).into(logo)
                "ligue1" -> Picasso.get().load(R.drawable.ic_lone).into(logo)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return LeagueViewHolder(view)
    }

    override fun getItemCount() = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val news = leagues.get(position)
        holder.view.setOnClickListener{ click(news.logo) }
        holder.bindview(news)
    }
}