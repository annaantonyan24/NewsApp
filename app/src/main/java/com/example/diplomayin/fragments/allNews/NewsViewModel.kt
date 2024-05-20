package com.example.diplomayin.fragments.allNews

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ActionResult
import com.example.data.model.model.room.NewsDataModel
import com.example.diplomayin.analytics.Analytic
import com.example.diplomayin.analytics.UserTapCategory
import com.example.domain.interactors.NewsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsInteractor: NewsInteractor, private val analytic: Analytic) :
    ViewModel() {

    private val _list: MutableStateFlow<List<NewsDataModel>?> by lazy { MutableStateFlow(null) }
    val list = _list.asSharedFlow()

    private val _errorNullData = MutableStateFlow<String?>(null)

    fun getList(category: String) {
        viewModelScope.launch {
            when (val result = newsInteractor(if (category != "All News") category else "")) {
                is ActionResult.Success -> {
                    _list.emit(result.data)
                }

                is ActionResult.Error -> {
                    _errorNullData.value = result.errors.errorMessage
                }
            }
        }
    }

    fun sendCategoryEvent(context: Context, category: String) {

        viewModelScope.launch {
            analytic.send(UserTapCategory(context, category))
        }

    }

}