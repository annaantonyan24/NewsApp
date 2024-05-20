package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.model.model.room.NewsDataModel
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.GetSavedNewsInteractor
import kotlinx.coroutines.withContext

class GetSavedNewsUseCase(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : GetSavedNewsInteractor {

    override suspend fun getSavedNews(): List<NewsDataModel> = withContext(dispatcher.io) {
        val roomData = newsRepository.getSavedNews()
        if (roomData.isNotEmpty()) {
            return@withContext roomData
        } else {
            return@withContext emptyList()
        }
    }
}