package com.example.diplomayin.fragments.developersNews

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.adapters.NewsListAdapter
import com.example.diplomayin.databinding.FragmentNewsDevelopersBinding
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DevelopersNewsFragment : FragmentBaseMVVM<FragmentNewsDevelopersBinding>() {

    override val binding: FragmentNewsDevelopersBinding by viewBinding()
    private val viewModel: DevelopersNewsViewModel by viewModel()
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