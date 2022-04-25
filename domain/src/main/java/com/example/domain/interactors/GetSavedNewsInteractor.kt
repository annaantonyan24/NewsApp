package com.example.domain.interactors

import com.example.data.model.model.room.NewsDataModel

interface GetSavedNewsInteractor {

    suspend fun getSavedNews(): List<NewsDataModel>
}