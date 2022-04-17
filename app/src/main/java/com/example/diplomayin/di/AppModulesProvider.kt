package com.example.diplomayin.di

import com.example.diplomayin.activity.mainActivity.SharedViewModel
import com.example.diplomayin.fragments.allNews.AllNewsViewModel
import com.example.diplomayin.fragments.technologyNews.TechnologyNewsViewModel
import com.example.diplomayin.fragments.healthNews.HealthNewsViewModel
import com.example.diplomayin.fragments.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AllNewsViewModel(get()) }
    viewModel { HealthNewsViewModel(get()) }
    viewModel { TechnologyNewsViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { SharedViewModel(get(),get(),get()) }
}