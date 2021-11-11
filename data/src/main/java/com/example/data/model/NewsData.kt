package com.example.data.model

data class NewsData(
    val author: String?,
    val category: String,
    val country: String,
    val description: String,
    val image: Any?,
    val language: String,
    val published_at: String,
    val source: String,
    val title: String,
    val url: String
)