package com.example.domain.utils

import com.example.data.model.model.response.Article
import com.example.data.model.model.room.NewsDataModel
import com.example.domain.model.Data

fun Article.toNews(): Data = Data(
    author,
    title,
    description,
    urlToImage,
    publishedAt,
    url,
    content
)

fun Article.toNewsModel(): NewsDataModel = NewsDataModel(
    author = author,
    title = title,
    description = description,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    url = url,
    content = content
)

fun NewsDataModel.toData(): Data = Data(
    author = author,
    title = title,
    description = description,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    url = url,
    content = content,
    isSaved = isSaved
)