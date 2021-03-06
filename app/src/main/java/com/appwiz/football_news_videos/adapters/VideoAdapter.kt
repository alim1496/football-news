package com.appwiz.football_news_videos.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.activities.PlayerActivity
import com.appwiz.football_news_videos.database.EntityMatch
import com.appwiz.football_news_videos.models.Match
import com.appwiz.football_news_videos.utils.UtilMethods
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class VideoAdapter(private var videos:List<Match>, private val click: (String) -> Unit)
    : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val play:ImageView = itemView.findViewById(R.id.play)
        fun bindView(match:Match) {
            val title:TextView = itemView.findViewById(R.id.title)
            val comp:TextView = itemView.findViewById(R.id.competition)
            val ago:TextView = itemView.findViewById(R.id.time_ago)
            val thumb:ImageView = itemView.findViewById(R.id.thumbnail)

            title.text = match.title
            comp.text = match.competition.name
            Picasso.get().load(match.thumbnail).placeholder(R.drawable.video_ph).into(thumb)
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
            val time = sdf.parse(match.date)!!
            ago.text = UtilMethods().findTimeAgo(time.time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos.get(position)
        holder.play.setOnClickListener { click(video.embed) }
        holder.bindView(video)
    }

    fun setData(newData:List<Match>) {
        videos = newData
        notifyDataSetChanged()
    }
}