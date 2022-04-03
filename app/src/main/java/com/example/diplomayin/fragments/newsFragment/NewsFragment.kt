package com.example.diplomayin.fragments.newsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diplomayin.R
import com.example.diplomayin.adapters.ViewPagerAdapter
import com.example.diplomayin.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val fragmentsArray = arrayOf(
        "All News",
        "Health",
        "Technology"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentsArray[position]
        }.attach()

        return binding.root
    }

}