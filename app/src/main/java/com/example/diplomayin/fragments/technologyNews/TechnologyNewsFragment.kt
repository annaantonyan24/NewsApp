package com.example.diplomayin.fragments.technologyNews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.adapters.NewsListAdapter
import com.example.diplomayin.databinding.FragmentNewsTechnologyBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TechnologyNewsFragment : FragmentBaseMVVM<FragmentNewsTechnologyBinding>() {

    override val binding: FragmentNewsTechnologyBinding by viewBinding()
    private val viewModel: TechnologyNewsViewModel by viewModel()
    private val bundle = Bundle()

    private var newsAdapter = NewsListAdapter({ data ->
        bundle.putParcelable(NewsConstants.NEWS_BUNDLE, data)

        view?.let { view ->
            Navigation.findNavController(view).navigate(R.id.navigation_details, bundle)
        }
    }, {

    }) {

    }

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