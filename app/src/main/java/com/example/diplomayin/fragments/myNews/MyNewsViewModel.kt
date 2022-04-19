package com.example.diplomayin.fragments.myNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.model.room.MyNewsDataModel
import com.example.domain.interactors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MyNewsViewModel(
    private val getMyNewsInteractor: GetMyNewsInteractor,
    private val deleteMyNewsInteractor: DeleteMyNewsInteractor
) : ViewModel() {

    private val _getMyNews: MutableSharedFlow<List<MyNewsDataModel>> by lazy { MutableSharedFlow() }
    val getMyNews = _getMyNews.asSharedFlow()

    init {
        myNews()
    }

    private fun myNews() {
        viewModelScope.launch {
            val result = getMyNewsInteractor.getMyNews().reversed()
            _getMyNews.emit(result)
        }
    }

    fun deleteMyNews(news: MyNewsDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMyNewsInteractor.deleteMyNews(news)
            myNews()
        }
    }
}