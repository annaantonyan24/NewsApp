package com.example.domain.interactors

import com.example.data.model.model.room.MyNewsDataModel

interface UpdateMyNewsInteractor {
    suspend fun updateMyNews(news: MyNewsDataModel)
}