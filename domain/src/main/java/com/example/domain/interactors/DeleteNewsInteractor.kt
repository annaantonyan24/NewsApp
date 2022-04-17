package com.example.domain.interactors

import com.example.domain.model.Data

interface DeleteNewsInteractor {
    suspend fun deleteNews(news: Data)
}