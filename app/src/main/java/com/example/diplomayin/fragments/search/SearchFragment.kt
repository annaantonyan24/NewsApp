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

    private var adapterNews = AdapterSearch {
        bundle.putParcelable(NewsConstants.NEWS_BUNDLE, it)

        view?.let { view ->
            Navigation.findNavController(view).navigate(R.id.navigation_details, bundle)
        }
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


var list = mutableListOf(
    Data(
        "CNN",
        "As Chinese President Xi Jinping prepares for a phone call with his US counterpart on Friday, the war in Ukraine looms large with Beijing's position on Russia's invasion under increasing international",
        "Ukraine said it launched a counteroffensive aimed at gaining control of Kyiv's suburbs after NATO officials said Russia's offensive to capture.",
        "54578",
        "Russia invades Ukraine: Live updates - CNN",
        "https://www.cnn.com/europe/live-news/ukraine-russia-putin-news-03-18-22/index.html",
        "https://dynaimage.cdn.cnn.com/cnn/digital-images/org/67b34f21-4faa-437b-98f5-57b1daec2b88.JPG"
    ),
    Data(
        "CNN",
        "As Chinese President Xi Jinping prepares for a phone call with his US counterpart on Friday, the war in Ukraine looms large with Beijing's position on Russia's invasion under increasing international",
        "Ukraine said it launched a counteroffensive aimed at gaining control of Kyiv's suburbs after NATO officials said Russia's offensive to capture.",
        "54578",
        "Russia invades Ukraine: Live updates - CNN",
        "https://www.cnn.com/europe/live-news/ukraine-russia-putin-news-03-18-22/index.html",
        "https://dynaimage.cdn.cnn.com/cnn/digital-images/org/67b34f21-4faa-437b-98f5-57b1daec2b88.JPG"
    ),
    Data(
        "CNN",
        "As Chinese President Xi Jinping prepares for a phone call with his US counterpart on Friday, the war in Ukraine looms large with Beijing's position on Russia's invasion under increasing international",
        "Ukraine said it launched a counteroffensive aimed at gaining control of Kyiv's suburbs after NATO officials said Russia's offensive to capture.",
        "54578",
        "Russia invades Ukraine: Live updates - CNN",
        "https://www.cnn.com/europe/live-news/ukraine-russia-putin-news-03-18-22/index.html",
        "https://dynaimage.cdn.cnn.com/cnn/digital-images/org/67b34f21-4faa-437b-98f5-57b1daec2b88.JPG"
    ),
    Data(
        "CNN",
        "As Chinese President Xi Jinping prepares for a phone call with his US counterpart on Friday, the war in Ukraine looms large with Beijing's position on Russia's invasion under increasing international",
        "Ukraine said it launched a counteroffensive aimed at gaining control of Kyiv's suburbs after NATO officials said Russia's offensive to capture.",
        "54578",
        "Russia invades Ukraine: Live updates - CNN",
        "https://www.cnn.com/europe/live-news/ukraine-russia-putin-news-03-18-22/index.html",
        "https://dynaimage.cdn.cnn.com/cnn/digital-images/org/67b34f21-4faa-437b-98f5-57b1daec2b88.JPG"
    ),
    Data(
        "CNN",
        "As Chinese President Xi Jinping prepares for a phone call with his US counterpart on Friday, the war in Ukraine looms large with Beijing's position on Russia's invasion under increasing international",
        "Ukraine said it launched a counteroffensive aimed at gaining control of Kyiv's suburbs after NATO officials said Russia's offensive to capture.",
        "54578",
        "Russia invades Ukraine: Live updates - CNN",
        "https://www.cnn.com/europe/live-news/ukraine-russia-putin-news-03-18-22/index.html",
        "https://dynaimage.cdn.cnn.com/cnn/digital-images/org/67b34f21-4faa-437b-98f5-57b1daec2b88.JPG"
    )
)
