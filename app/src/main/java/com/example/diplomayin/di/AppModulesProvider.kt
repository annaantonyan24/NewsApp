package com.example.diplomayin.di

import com.example.diplomayin.fragments.allNews.AllNewsViewModel
import com.example.diplomayin.fragments.developersNews.DevelopersNewsViewModel
import com.example.diplomayin.fragments.newsDetails.NewsDetailsViewModel
import com.example.diplomayin.fragments.popularNews.PopularNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AllNewsViewModel(get()) }
    viewModel { PopularNewsViewModel(get()) }
    viewModel { DevelopersNewsViewModel(get()) }
    viewModel { NewsDetailsViewModel(get()) }
}