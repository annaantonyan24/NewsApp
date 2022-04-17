package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.DeleteNewsInteractor
import com.example.domain.model.Data
import com.example.domain.utils.toNewsModel
import kotlinx.coroutines.withContext

class DeleteNewsUseCase(
    private val newsRepo: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : DeleteNewsInteractor  {
    override suspend fun deleteNews(news: Data) = withContext(dispatcher.io) {
        newsRepo.deleteNews(news.toNewsModel())
    }
}