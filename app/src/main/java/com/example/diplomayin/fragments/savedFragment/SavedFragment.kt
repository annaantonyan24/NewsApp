package com.example.diplomayin.fragments.savedFragment

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.activity.mainActivity.SavedViewModel
import com.example.diplomayin.adapters.AdapterDB
import com.example.diplomayin.databinding.FragmentSavedBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedFragment: FragmentBaseMVVM<FragmentSavedBinding>() {

    override val binding : FragmentSavedBinding by viewBinding()
    private val savedViewModel: SavedViewModel by viewModel()
    private val bundle = Bundle()

    private var adapterNews = AdapterDB({
        bundle.putParcelable(NewsConstants.NEWS_BUNDLE, it)

        view?.let { view ->
            Navigation.findNavController(view).navigate(R.id.savedNewsDetailsFragment, bundle)
        }
    }) {
        savedViewModel.deleteNews(it)
    }

    override fun onView() {
        savedViewModel.savedNews()

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
        onEach(savedViewModel.getSavedNews) {
            if(it.isEmpty()){
                binding.tvText.visibility = View.VISIBLE
            }
            adapterNews.differ.submitList(it)
        }
    }

}