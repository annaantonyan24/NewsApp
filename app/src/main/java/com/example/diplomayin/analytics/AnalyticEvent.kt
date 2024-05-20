package com.example.diplomayin.analytics

open class AnalyticEvent(
    val name: String,
    val parameters: Map<String, String> = emptyMap()
)