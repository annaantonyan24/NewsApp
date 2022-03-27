package com.example.diplomayin.fragments.allNews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.adapters.NewsListAdapter
import com.example.diplomayin.databinding.FragmentAllNewsBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllNewsFragment : FragmentBaseMVVM<FragmentAllNewsBinding>() {

    private val viewModel: AllNewsViewModel by viewModel()
    override val binding: FragmentAllNewsBinding by viewBinding()
    private val bundle = Bundle()

    private var newsAdapter = NewsListAdapter({

            bundle.putParcelable(NewsConstants.NEWS_BUNDLE, it)

            view?.let { view ->
                Navigation.findNavController(view).navigate(R.id.navigation_details, bundle)
            }
        }) {

        }

    @SuppressLint("NotifyDataSetChanged")
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
        onEach(viewModel.list) {
            newsAdapter.differ.submitList(it)

            binding.swipeRefreshLayout.setOnRefreshListener {
                newsAdapter.differ.submitList(it)
                newsAdapter.notifyDataSetChanged()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

}