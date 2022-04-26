package com.example.domain.usecases

import com.example.core.ActionResult
import com.example.core.CallException
import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.data.model.model.room.NewsDataModel
import com.example.data.repository.NewsRepository
import com.example.domain.interactors.NewsInteractor
import kotlinx.coroutines.withContext
import com.example.data.util.Constants.Companion.ERROR_DATA
import com.example.data.util.Constants.Companion.ERROR_NULL_DATA
import com.example.domain.model.Data
import com.example.domain.utils.toNews
import com.example.domain.utils.toNewsModel


class NewsUseCase(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcherProvider
) : NewsInteractor {
    override suspend fun invoke(): ActionResult<List<NewsDataModel>> = withContext(dispatcher.io) {
        when (val apiData = newsRepository.getAllNewsData()) {
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
                        ERROR_NULL_DATA
                    )
                )
            }

            is ActionResult.Error -> {
                ActionResult.Error(
                    CallException(
                        ERROR_DATA,
                        apiData.errors.errorMessage
                    )
                )
            }
        }
    }
}