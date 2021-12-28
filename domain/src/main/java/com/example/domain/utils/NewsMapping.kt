package com.example.domain.utils

import com.example.data.model.Article
import com.example.domain.model.Data

fun Article.toNews(): Data = Data(
    author,
    title,
    description,
    urlToImage,
    publishedAt
)