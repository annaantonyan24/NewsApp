package com.example.diplomayin.fragments.addNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.model.room.MyNewsDataModel
import com.example.domain.interactors.InsertMyNewsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewsViewModel(
    private val insertMyNewsInteractor: InsertMyNewsInteractor,
) : ViewModel() {

    fun insertMyNews(news: MyNewsDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMyNewsInteractor.insertMyNews(news)
        }
    }

}