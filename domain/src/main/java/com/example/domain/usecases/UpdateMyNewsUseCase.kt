package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.UpdateMyNewsInteractor
import kotlinx.coroutines.withContext

class UpdateMyNewsUseCase(
    private val newsRepo: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : UpdateMyNewsInteractor {
    override suspend fun updateMyNews(news: MyNewsDataModel) = withContext(dispatcher.io) {
        newsRepo.updateMyNews(news)
    }
}