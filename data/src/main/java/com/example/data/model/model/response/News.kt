package com.example.data.model.model.response

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)