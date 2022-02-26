package com.example.diplomayin.fragments.newsDetails

import androidx.lifecycle.ViewModel
import com.example.domain.interactors.NewsInteractor
import com.example.domain.model.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow


class NewsDetailsViewModel(private val newsInteractor: NewsInteractor) : ViewModel() {

    private val _news: MutableStateFlow<Data?> by lazy { MutableStateFlow(null) }
    val news = _news.asSharedFlow()

    private val _errorNullData = MutableStateFlow<String?>(null)

    fun getItem(id: String) {


    }

}