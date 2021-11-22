package com.example.diplomayin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.diplomayin.fragments.AllNewsFragment
import com.example.diplomayin.fragments.NewsCategoryFragment

private const val NUM_TABS = 2

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM_TABS


    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return AllNewsFragment()
        }
        return NewsCategoryFragment()
    }
}