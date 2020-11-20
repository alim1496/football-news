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

class NewsAdapter(private var newslist: List<NewsSite>, private val click: (String) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view:View = itemView.findViewById(R.id.name_holder)

        fun bindview(news:NewsSite) {
            val title:TextView = itemView.findViewById(R.id.title)
            val logo:ImageView = itemView.findViewById(R.id.logo)
            title.text = news.name
            when (news.logo) {
                "bbc" -> Picasso.get().load(R.drawable.ic_bbc).into(logo)
                "goal" -> Picasso.get().load(R.drawable.ic_goal).into(logo)
                "sky" -> Picasso.get().load(R.drawable.ic_sky).into(logo)
                "espn" -> Picasso.get().load(R.drawable.ic_espn).into(logo)
                "guardian" -> Picasso.get().load(R.drawable.ic_guardian).into(logo)
                "euro" -> Picasso.get().load(R.drawable.ic_euro).into(logo)
                "mirror" -> Picasso.get().load(R.drawable.ic_mirror).into(logo)
                "independent" -> Picasso.get().load(R.drawable.ic_independent).into(logo)
                "marca" -> Picasso.get().load(R.drawable.ic_marca).into(logo)
                "uefa" -> Picasso.get().load(R.drawable.ic_uefa).into(logo)
            }

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
        holder.view.setOnClickListener { click(news.url) }
        holder.bindview(news)
    }
}