package com.example.domain.interactors

import com.example.data.model.model.room.MyNewsDataModel

interface DeleteMyNewsInteractor {

    suspend fun deleteMyNews(news: MyNewsDataModel)
}