package com.example.diplomayin.fragments

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.diplomayin.adapters.ViewPagerAdapter
import com.example.diplomayin.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayoutMediator

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val adapter by lazy { ViewPagerAdapter(childFragmentManager, lifecycle) }

    private val fragmentsArray = arrayOf(
        "All News",
        "Category"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val layoutParams = tabLayout.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.topMargin = 120


        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentsArray[position]
        }.attach()

        return binding.root
    }


}