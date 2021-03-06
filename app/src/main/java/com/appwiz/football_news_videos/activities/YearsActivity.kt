package com.appwiz.football_news_videos.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.appwiz.football_news_videos.R
import com.appwiz.football_news_videos.adapters.ResultAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class YearsActivity : AppCompatActivity() {

    private lateinit var viewpager: ViewPager2
    private lateinit var tabs: TabLayout
    private lateinit var title:TextView
    private lateinit var season:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_years)

        viewpager = findViewById(R.id.view_pager)
        tabs = findViewById(R.id.tabs)
        title = findViewById(R.id.league_name)
        season = findViewById(R.id.season)

        val name = intent.getStringExtra("league_name")
        title.text = name
        season.text = "2019-2020"

        viewpager.adapter = ResultAdapter(this, name!!)

        TabLayoutMediator(tabs, viewpager) { tab, position->
            when (position) {
                0 -> tab.text = "Results"
                1 -> tab.text = "Standing"
                2 -> tab.text = "Scorers"
                3 -> tab.text = "Assists"
            }
        }.attach()
    }

}