package com.example.domain.usecases

import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.GetMyNewsInteractor
import kotlinx.coroutines.withContext

class GetMyNewsUseCase(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
): GetMyNewsInteractor {
    override suspend fun getMyNews(): List<MyNewsDataModel> = withContext(dispatcher.io){
        newsRepository.getMyNews()
    }

}