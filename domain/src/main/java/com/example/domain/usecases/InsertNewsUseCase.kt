package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.InsertNewsInteractor
import com.example.domain.model.Data
import com.example.domain.utils.toNewsModel
import kotlinx.coroutines.withContext


class InsertNewsUseCase(
    private val newsRepo: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : InsertNewsInteractor {

    override suspend fun insertNews(news: Data) = withContext(dispatcher.io) {
       newsRepo.insertNews(news.toNewsModel())
    }

}