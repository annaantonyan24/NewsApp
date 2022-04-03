package com.example.diplomayin.activity.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.NewsDao
import com.example.data.model.model.room.NewsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SharedViewModel(private val newsDao: NewsDao) : ViewModel() {

    fun getAllNews() : LiveData<List<NewsDataModel>> = newsDao.getAllNews()

    fun insertNews(news: NewsDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            newsDao.insertData(news)
        }
    }

}