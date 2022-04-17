package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.InsertMyNewsInteractor
import kotlinx.coroutines.withContext

class InsertMyNewsUseCase(
    private val newsRepo: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : InsertMyNewsInteractor {

    override suspend fun insertMyNews(news: MyNewsDataModel) = withContext(dispatcher.io) {
        newsRepo.insertMyNews(news)
    }
}