package com.example.domain.interactors

import com.example.data.model.model.room.NewsDataModel

interface DeleteNewsInteractor {
    suspend fun deleteNews(news: NewsDataModel)
}