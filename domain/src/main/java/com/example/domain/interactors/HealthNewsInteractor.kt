package com.example.domain.interactors

import com.example.core.ActionResult
import com.example.data.model.model.room.NewsDataModel
import com.example.domain.model.Data

interface HealthNewsInteractor {

    suspend operator fun invoke(): ActionResult<List<NewsDataModel>>

}