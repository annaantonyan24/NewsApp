package com.example.diplomayin.fragments.healthNews

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.model.room.NewsDataModel
import com.example.diplomayin.FragmentBaseMVVM
import com.example.diplomayin.R
import com.example.diplomayin.activity.mainActivity.SavedViewModel
import com.example.diplomayin.adapters.NewsListAdapter
import com.example.diplomayin.databinding.FragmentNewsHealthBinding
import com.example.diplomayin.utils.NewsConstants
import com.example.diplomayin.utils.viewBinding
import com.example.domain.model.Data
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HealthNewsFragment : FragmentBaseMVVM<FragmentNewsHealthBinding>() {

    private val viewModel: HealthNewsViewModel by viewModel()
    override val binding: FragmentNewsHealthBinding by viewBinding()
    private val bundle = Bundle()
    private val savedViewModel: SavedViewModel by sharedViewModel()

    private var newsAdapter = NewsListAdapter({ data ->
        bundle.putParcelable(NewsConstants.NEWS_BUNDLE, data)

        view?.let { view ->
            Navigation.findNavController(view).navigate(R.id.navigation_details, bundle)
        }
    }, { data: NewsDataModel ->
        savedViewModel.insertNews(data)
    }) {
        savedViewModel.deleteNews(it)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onView() {
        binding.swipeRefreshLayout.isRefreshing = true
        if(!isOnline()){
            binding.tvText.visibility = View.VISIBLE
            binding.swipeRefreshLayout.isRefreshing = false
        }
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
        onEach(viewModel.list) {
            newsAdapter.differ.submitList(it)
            binding.swipeRefreshLayout.isRefreshing = false

            binding.swipeRefreshLayout.setOnRefreshListener {
                newsAdapter.differ.submitList(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }

}