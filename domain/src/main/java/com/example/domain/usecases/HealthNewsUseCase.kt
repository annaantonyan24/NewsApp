package com.example.domain.usecases

import com.example.core.ActionResult
import com.example.core.CallException
import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.model.model.room.NewsDataModel
import com.example.data.repository.NewsRepository
import com.example.data.util.Constants
import com.example.domain.interactors.HealthNewsInteractor
import com.example.domain.utils.toNewsModel
import kotlinx.coroutines.withContext

class HealthNewsUseCase(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : HealthNewsInteractor {
    override suspend fun invoke(): ActionResult<List<NewsDataModel>> = withContext(dispatcher.io) {
        when (val apiData = newsRepository.getHealthNewsData()) {
            is ActionResult.Success -> {
                apiData.data?.let { it ->
                    val mapList = it.articles.map { it.toNewsModel() }
                    val dbData = newsRepository.getSavedNews()
                    mapList.forEach { mapNews ->
                        dbData.forEach { dbNews ->
                            if (mapNews.url == dbNews.url) {
                                mapNews.isSaved = true
                                mapNews.newsID = dbNews.newsID
                            }
                        }
                    }
                    ActionResult.Success(mapList)
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