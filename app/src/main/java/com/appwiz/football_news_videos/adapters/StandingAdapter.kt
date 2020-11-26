package com.appwiz.football_news_videos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.models.Standing

class StandingAdapter(private var standings:List<Standing>)
    : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {

    class StandingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindVIew(standing:Standing) {
            val rank:TextView = itemView.findViewById(R.id.rank)
            val name:TextView = itemView.findViewById(R.id.name)
            val matches:TextView = itemView.findViewById(R.id.matches)
            val win:TextView = itemView.findViewById(R.id.win)
            val draw:TextView = itemView.findViewById(R.id.draw)
            val loss:TextView = itemView.findViewById(R.id.loss)
            val diff:TextView = itemView.findViewById(R.id.diff)
            val points:TextView = itemView.findViewById(R.id.points)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.standing_item, parent, false)
        return StandingViewHolder(view)
    }

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        val standing = standings.get(position)
        holder.bindVIew(standing)
    }

    fun appendData(data:List<Standing>) {
        standings = data
        notifyDataSetChanged()
    }
}