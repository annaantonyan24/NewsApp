package com.example.diplomayin.fragments.popularNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ActionResult
import com.example.domain.interactors.PopularNewsInteractor
import com.example.domain.model.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PopularNewsViewModel(private val popularNewsInteractor: PopularNewsInteractor) : ViewModel() {

    private val _list: MutableStateFlow<List<Data>?> by lazy { MutableStateFlow(null) }
    val list = _list.asSharedFlow()

    private val _errorNullData = MutableStateFlow<String?>(null)

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            when (val result = popularNewsInteractor()) {
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