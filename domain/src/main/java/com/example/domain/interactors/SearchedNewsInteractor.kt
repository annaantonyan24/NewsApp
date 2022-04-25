package com.example.domain.interactors

import com.example.core.ActionResult
import com.example.data.model.model.room.NewsDataModel
import com.example.domain.model.Data

interface SearchedNewsInteractor {
    suspend operator fun invoke(
        title: String,
    ): ActionResult<List<NewsDataModel>>
}