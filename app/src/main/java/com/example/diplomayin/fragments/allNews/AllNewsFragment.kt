package com.example.diplomayin.fragments.allNews

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.Article
import com.example.data.model.Source
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.adapters.NewsListAdapter
import com.example.diplomayin.databinding.FragmentAllNewsBinding
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllNewsFragment : FragmentBaseMVVM<FragmentAllNewsBinding>() {

    private val viewModel: AllNewsViewModel by viewModel()
    override val binding: FragmentAllNewsBinding by viewBinding()

    private var newsAdapter = NewsListAdapter()
    private val placesList = arrayListOf<Article>()

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
        }
    }

}