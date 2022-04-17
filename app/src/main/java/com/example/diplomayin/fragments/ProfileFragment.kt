package com.example.diplomayin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diplomayin.R
import com.example.diplomayin.adapters.MyNewsViewPagerAdapter
import com.example.diplomayin.adapters.ViewPagerAdapter
import com.example.diplomayin.databinding.FragmentNewsBinding
import com.example.diplomayin.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = MyNewsViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentsArray[position]
        }.attach()
        return binding.root
    }

    private val fragmentsArray = arrayOf(
        "Add News",
        "My News"
    )

}