package com.example.domain.utils

import com.example.data.model.model.response.Article
import com.example.data.model.model.room.NewsDataModel
import com.example.domain.model.Data

fun Article.toNewsModel(): NewsDataModel = NewsDataModel(
    author = author,
    title = title,
    description = description,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    url = url,
    content = content
)