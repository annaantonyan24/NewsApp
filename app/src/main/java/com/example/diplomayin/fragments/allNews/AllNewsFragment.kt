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


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        initList()
////        initRecyclerView()
//
//    }

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


    private fun initRecyclerView() {

        with(binding) {
            rvNews.layoutManager = LinearLayoutManager(context)
            rvNews.adapter = newsAdapter
        }

    }

    private fun initList() {

        placesList.add(
            Article(
                "CALVIN YANG",
                "London (CNN)Nations around the world are racing to identify how many cases of the Omicron Covid-19",
                "Latest updates: Nicola Sturgeon says Scottish and Welsh governments think tougher restrictions would help identify cases coming in to UK",
                "2021-11-29T12:40:13Z",
                Source("123", "name"),
                "UK Covid live: Scottish and Welsh governments call for eight-day quarantine for all new arrivals to UK - The Guardian",
                "https://www.theguardian.com/politics/live/2021/nov/29/uk-covid-live-omicron-cases-england-scotland-health-minister-vaccines-coronavirus-latest-update",
                "https://c.ndtvimg.com/2020-12/0sk5pp9g_farmers-protest-delhi-singhu-border-_625x300_01_December_20.jpg",
            )
        )
        placesList.add(
            Article(
                "Anna",
                "London (CNN)Nations around the world are racing to identify how many cases of the Omicron Covid-19",
                "No data on farmers' deaths during protests, hence question of financial assistance doesn't arise: government to parliament",
                "2021-12-01T06:29:00Z",
                Source("123", "name"),
                "No Farmer Death Data During Protests, So No Question Of Aid: Government - NDTV",
                "https://www.theguardian.com/politics/live/2021/nov/29/uk-covid-live-omicron-cases-england-scotland-health-minister-vaccines-coronavirus-latest-update",
                "https://i.guim.co.uk/img/media/ee783763bf45d24ed99df59ae2026fa41433f501/28_6_5643_3386/master/5643.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctbGl2ZS5wbmc&enable=upscale&s=daa4227b7572d17757ee668c6e840fab",
            )
        )
        placesList.add(
            Article(
                "Anna",
                "London (CNN)Nations around the world are racing to identify how many cases of the Omicron Covid-19",
                "Latest updates: Nicola Sturgeon says Scottish and Welsh governments think tougher restrictions would help identify cases coming in to UK",
                "2021-11-29T12:40:13Z",
                Source("123", "name"),
                "UK Covid live: Scottish and Welsh governments call for eight-day quarantine for all new arrivals to UK - The Guardian",
                "https://www.theguardian.com/politics/live/2021/nov/29/uk-covid-live-omicron-cases-england-scotland-health-minister-vaccines-coronavirus-latest-update",
                "https://i.guim.co.uk/img/media/ee783763bf45d24ed99df59ae2026fa41433f501/28_6_5643_3386/master/5643.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctbGl2ZS5wbmc&enable=upscale&s=daa4227b7572d17757ee668c6e840fab",
            )
        )
        placesList.add(
            Article(
                "Anna",
                "London (CNN)Nations around the world are racing to identify how many cases of the Omicron Covid-19",
                "Latest updates: Nicola Sturgeon says Scottish and Welsh governments think tougher restrictions would help identify cases coming in to UK",
                "2021-11-29T12:40:13Z",
                Source("123", "name"),
                "UK Covid live: Scottish and Welsh governments call for eight-day quarantine for all new arrivals to UK - The Guardian",
                "https://www.theguardian.com/politics/live/2021/nov/29/uk-covid-live-omicron-cases-england-scotland-health-minister-vaccines-coronavirus-latest-update",
                "https://i.guim.co.uk/img/media/ee783763bf45d24ed99df59ae2026fa41433f501/28_6_5643_3386/master/5643.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctbGl2ZS5wbmc&enable=upscale&s=daa4227b7572d17757ee668c6e840fab",
            )
        )
        placesList.add(
            Article(
                "Anna",
                "London (CNN)Nations around the world are racing to identify how many cases of the Omicron Covid-19",
                "Latest updates: Nicola Sturgeon says Scottish and Welsh governments think tougher restrictions would help identify cases coming in to UK",
                "2021-11-29T12:40:13Z",
                Source("123", "name"),
                "UK Covid live: Scottish and Welsh governments call for eight-day quarantine for all new arrivals to UK - The Guardian",
                "https://www.theguardian.com/politics/live/2021/nov/29/uk-covid-live-omicron-cases-england-scotland-health-minister-vaccines-coronavirus-latest-update",
                "https://i.guim.co.uk/img/media/ee783763bf45d24ed99df59ae2026fa41433f501/28_6_5643_3386/master/5643.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctbGl2ZS5wbmc&enable=upscale&s=daa4227b7572d17757ee668c6e840fab",
            )
        )

    }

}