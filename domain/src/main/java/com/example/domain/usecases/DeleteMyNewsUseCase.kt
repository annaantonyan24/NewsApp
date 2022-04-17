package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.DeleteMyNewsInteractor
import kotlinx.coroutines.withContext

class DeleteMyNewsUseCase(
    private val newsRepo: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : DeleteMyNewsInteractor {
    override suspend fun deleteMyNews(news: MyNewsDataModel) = withContext(dispatcher.io) {
        newsRepo.deleteMyNews(news)
    }
}