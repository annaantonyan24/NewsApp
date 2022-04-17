package com.example.diplomayin.fragments.savedFragment

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.activity.mainActivity.SharedViewModel
import com.example.diplomayin.adapters.AdapterDB
import com.example.diplomayin.adapters.AdapterSearch
import com.example.diplomayin.databinding.FragmentSavedBinding
import com.example.diplomayin.databinding.FragmentSearchBinding
import com.example.diplomayin.fragments.search.SearchViewModel
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedFragment: FragmentBaseMVVM<FragmentSavedBinding>() {

    override val binding : FragmentSavedBinding by viewBinding()
    private val sharedViewModel: SharedViewModel by sharedViewModel()
    private val bundle = Bundle()

    private var adapterNews = AdapterDB({
        bundle.putParcelable(NewsConstants.NEWS_BUNDLE, it)

        view?.let { view ->
            Navigation.findNavController(view).navigate(R.id.navigation_details, bundle)
        }
    }) {

    }

    override fun onView() {

        with(binding) {
            rvNews.apply {
                context?.let {
                    layoutManager = LinearLayoutManager(it)
                    setHasFixedSize(true)
                    adapter = adapterNews
                }
            }

        }
    }

    override fun onEach() {
        onEach(sharedViewModel.getSavedNews) {
            adapterNews.differ.submitList(it)
        }
    }

}