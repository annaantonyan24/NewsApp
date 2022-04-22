package com.example.diplomayin.activity.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.model.room.NewsDataModel
import com.example.domain.interactors.DeleteNewsInteractor
import com.example.domain.interactors.GetSavedNewsInteractor
import com.example.domain.interactors.InsertNewsInteractor
import com.example.domain.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SavedViewModel(
    private val getSavedNewsInteractor: GetSavedNewsInteractor,
    private val insertNewsInteractor: InsertNewsInteractor,
    private val deleteNewsInteractor: DeleteNewsInteractor,
) : ViewModel() {

    private val _getSavedNews: MutableSharedFlow<List<Data>> by lazy { MutableSharedFlow() }
    val getSavedNews = _getSavedNews.asSharedFlow()

    init {
        savedNews()
    }

    fun savedNews() {
        viewModelScope.launch {
            val result = getSavedNewsInteractor.getSavedNews().reversed()
            _getSavedNews.emit(result)
        }
    }

    fun insertNews(news: Data) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNewsInteractor.insertNews(news)
        }
    }

    fun deleteNews(news: Data) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNewsInteractor.deleteNews(news)
        }
    }

//    fun updateSuggestedBooksList(dataModelId : Long,isAddedLibrary : Boolean){
//        viewModelScope.launch {
//            getSavedNewsInteractor.getSavedNews().forEachIndexed { index, element ->
//                if(getSavedNewsInteractor.getSavedNews()[index].url == )
//            }
//        }
//
//
//        for (i in itemSuggestedBooksList.indices){
//            if (itemSuggestedBooksList[i].id == dataModelId){
//                itemSuggestedBooksList[i] = itemSuggestedBooksList[i].copy(isAddedLibrary = isAddedLibrary)
//            }
//        }
//        itemSuggestedBooks = itemSuggestedBooks?.copy(booksList = itemSuggestedBooksList)
//        viewModelScope.launch {
//            _suggestedBooksList.emit(itemSuggestedBooks)
//        }
//    }

}