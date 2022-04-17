package com.example.domain.interactors

import com.example.data.model.model.room.MyNewsDataModel

interface GetMyNewsInteractor {

    suspend fun getMyNews(): List<MyNewsDataModel>
}