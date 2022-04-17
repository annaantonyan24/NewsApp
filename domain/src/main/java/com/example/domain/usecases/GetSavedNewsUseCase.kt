package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.GetSavedNewsInteractor
import com.example.domain.model.Data
import com.example.domain.utils.toData
import kotlinx.coroutines.withContext

class GetSavedNewsUseCase(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : GetSavedNewsInteractor {

    override suspend fun getSavedNews(): List<Data> = withContext(dispatcher.io) {
        val roomData = newsRepository.getSavedNews()
        if (roomData.isNotEmpty()) {
            return@withContext roomData.map { it.toData() }
        } else {
            return@withContext emptyList()
        }
    }
}