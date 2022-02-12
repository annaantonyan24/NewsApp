package com.example.diplomayin.di

import com.example.diplomayin.fragments.allNews.AllNewsFragment
import com.example.diplomayin.fragments.allNews.AllNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { AllNewsViewModel(get()) }
}