package com.appwiz.football_news_videos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.News
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newslist:MutableList<News> = ArrayList()

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindview(news:News) {
            val title:TextView = itemView.findViewById(R.id.title)
            val source:TextView = itemView.findViewById(R.id.source)
            val time:TextView = itemView.findViewById(R.id.time)
            val desc:TextView = itemView.findViewById(R.id.desc)
            val cover:ImageView = itemView.findViewById(R.id.cover)
            title.text = news.title
            source.text = news.source.name
            time.text = news.time
            desc.text = news.description
            Picasso.get().load(news.image).placeholder(R.drawable.ic_image_ph).into(cover)
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

    fun reload(newslist:List<News>) {
        this.newslist.clear()
        this.newslist.addAll(newslist)
        notifyDataSetChanged()
    }

    fun append(newslist:List<News>) {
        this.newslist.addAll(newslist)
        notifyItemRangeInserted(this.newslist.size - newslist.size, newslist.size)
    }
}