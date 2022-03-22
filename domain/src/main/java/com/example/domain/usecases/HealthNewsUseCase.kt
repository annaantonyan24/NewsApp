package com.example.domain.usecases

import com.example.core.ActionResult
import com.example.core.CallException
import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.repository.NewsRepository
import com.example.data.util.Constants
import com.example.domain.interactors.HealthNewsInteractor
import com.example.domain.model.Data
import com.example.domain.utils.toNews
import kotlinx.coroutines.withContext

class HealthNewsUseCase(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : HealthNewsInteractor {
    override suspend fun invoke(): ActionResult<List<Data>> = withContext(dispatcher.io) {
        when (val apiData = newsRepository.getHealthNewsData()) {
            is ActionResult.Success -> {
                apiData.data?.let { it ->
                    ActionResult.Success(it.articles.map { it.toNews() })
                } ?: ActionResult.Error(
                    CallException(
                        Constants.ERROR_NULL_DATA
                    )
                )
            }

            is ActionResult.Error -> {
                ActionResult.Error(
                    CallException(
                        Constants.ERROR_DATA,
                        apiData.errors.errorMessage
                    )
                )
            }
        }
    }
}