package com.appwiz.football_news_videos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.News
import com.appwiz.football_news_videos.models.NewsSite
import com.squareup.picasso.Picasso

class NewsAdapter(private var newslist: List<NewsSite>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindview(news:NewsSite) {
            val title:TextView = itemView.findViewById(R.id.title)
            val logo:ImageView = itemView.findViewById(R.id.logo)
            title.text = news.name
            Picasso.get().load(R.drawable.ic_bbc).into(logo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = newslist.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newslist.get(position)
        holder.bindview(news)
    }
}