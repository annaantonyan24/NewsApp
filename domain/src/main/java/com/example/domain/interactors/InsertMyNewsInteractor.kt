package com.example.domain.interactors

import com.example.data.model.model.room.MyNewsDataModel

interface InsertMyNewsInteractor {

    suspend fun insertMyNews(news: MyNewsDataModel)

}