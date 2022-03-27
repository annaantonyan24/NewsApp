package com.example.diplomayin.fragments.search

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.adapters.AdapterSearch
import com.example.diplomayin.databinding.FragmentSearchBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import com.example.domain.model.Data
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : FragmentBaseMVVM<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModel()
    override val binding: FragmentSearchBinding by viewBinding()
    private val bundle = Bundle()

    private var adapterNews = AdapterSearch({
        bundle.putParcelable(NewsConstants.NEWS_BUNDLE, it)

        view?.let { view ->
            Navigation.findNavController(view).navigate(R.id.navigation_details, bundle)
        }
    }) {

    }

    override fun onView() {

        binding.searchBar.queryHint = "Search for news articles"
        with(binding) {
            rvNews.apply {
                context?.let {
                    layoutManager = LinearLayoutManager(it)
                    setHasFixedSize(true)
                    adapter = adapterNews
                }
            }

            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.getList(it)
                    }
                    return true
                }

            })

        }
    }

    override fun onEach() {
        onEach(viewModel.list) {
            adapterNews.differ.submitList(it)
        }
    }
}

