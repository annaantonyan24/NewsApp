package com.example.diplomayin.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ActionResult
import com.example.data.model.model.room.NewsDataModel
import com.example.domain.interactors.SearchedNewsInteractor
import com.example.domain.model.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val newsInteractor: SearchedNewsInteractor) : ViewModel() {

    private val _list: MutableStateFlow<List<NewsDataModel>?> by lazy { MutableStateFlow(null) }
    val list = _list.asSharedFlow()

    private val _errorNullData = MutableStateFlow<String?>(null)

    fun getList(title: String) {
        viewModelScope.launch {
            when (val result = newsInteractor(title)) {
                is ActionResult.Success -> {
                    _list.emit(result.data)
                }
                is ActionResult.Error -> {
                    _errorNullData.value = result.errors.errorMessage
                }
            }
        }
    }
}