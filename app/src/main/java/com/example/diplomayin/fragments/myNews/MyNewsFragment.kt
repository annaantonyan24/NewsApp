package com.example.diplomayin.fragments.myNews

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.adapters.AdapterMyNews
import com.example.diplomayin.databinding.FragmentMyNewsBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyNewsFragment: FragmentBaseMVVM<FragmentMyNewsBinding>()  {
    private val viewModel: MyNewsViewModel by viewModel()
    override val binding: FragmentMyNewsBinding by viewBinding()
    private val bundle = Bundle()

    private var newsAdapter = AdapterMyNews({ data ->
        bundle.putParcelable(NewsConstants.NEWS_BUNDLE, data)

        view?.let { view ->
            Navigation.findNavController(view).navigate(R.id.myNewsDetailsFragment, bundle)
        }
    }, {
        viewModel.deleteMyNews(it)
    })

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
        onEach(viewModel.getMyNews) {
            newsAdapter.differ.submitList(it)
        }
    }

}