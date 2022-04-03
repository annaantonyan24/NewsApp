package com.example.domain.utils

import com.example.data.model.model.response.Article
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

