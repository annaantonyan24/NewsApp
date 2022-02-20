package com.example.diplomayin.fragments.popularNews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Article
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.adapters.NewsListAdapter
import com.example.diplomayin.databinding.FragmentAllNewsBinding
import com.example.diplomayin.databinding.FragmentNewsPopularBinding
import com.example.diplomayin.fragments.allNews.AllNewsViewModel
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularNewsFragment : FragmentBaseMVVM<FragmentNewsPopularBinding>() {

    private val viewModel: PopularNewsViewModel by viewModel()
    override val binding: FragmentNewsPopularBinding by viewBinding()

    private var newsAdapter = NewsListAdapter()

    override fun onView() {
        with(binding) {
            rvNews.apply {
                context?.let {
                    layoutManager = LinearLayoutManager(it)
                    setHasFixedSize(true)
                    adapter = newsAdapter
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onEach() {
        onEach(viewModel.list){
            newsAdapter.differ.submitList(it)

            binding.swipeRefreshLayout.setOnRefreshListener {
                newsAdapter.differ.submitList(it)
                newsAdapter.notifyDataSetChanged()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

}