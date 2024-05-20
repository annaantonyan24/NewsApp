package com.example.diplomayin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.diplomayin.fragments.allNews.NewsFragment
import com.example.diplomayin.fragments.technologyNews.TechnologyNewsFragment
import com.example.diplomayin.fragments.healthNews.HealthNewsFragment

private const val NUM_TABS = 3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NewsFragment()
            1 -> return HealthNewsFragment()
        }
        return TechnologyNewsFragment()
    }
}