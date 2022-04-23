package com.example.diplomayin.fragments.addNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.model.room.MyNewsDataModel
import com.example.domain.interactors.InsertMyNewsInteractor
import com.example.domain.interactors.UpdateMyNewsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewsViewModel(
    private val insertMyNewsInteractor: InsertMyNewsInteractor,
    private val updateMyNewsInteractor: UpdateMyNewsInteractor
) : ViewModel() {

    fun insertMyNews(news: MyNewsDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMyNewsInteractor.insertMyNews(news)
        }
    }

    fun updateMyNews(news: MyNewsDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            updateMyNewsInteractor.updateMyNews(news)
        }
    }

}