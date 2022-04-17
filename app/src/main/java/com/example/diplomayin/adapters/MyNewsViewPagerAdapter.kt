package com.example.diplomayin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.diplomayin.fragments.addNews.AddNewsFragment
import com.example.diplomayin.fragments.myNews.MyNewsFragment

private const val NUM_TABS = 2

class MyNewsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AddNewsFragment()
        }
        return MyNewsFragment()
    }
}