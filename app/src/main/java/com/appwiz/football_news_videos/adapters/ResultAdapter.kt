package com.appwiz.football_news_videos.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.appwiz.football_news_videos.fragments.ResultFragment

class ResultAdapter(fragment: FragmentActivity, private val name: String) : FragmentStateAdapter(fragment) {

    override fun getItemCount() : Int = 4

    override fun createFragment(position: Int): Fragment {
        val fragment = ResultFragment()
        fragment.arguments = Bundle().apply {
            putInt("result_type", position)
            putString("name_league", name)
        }
        return fragment
    }
}