package com.example.diplomayin.analytics

interface Analytic {
    /**
     * Отправка события
     */
    fun send(event: AnalyticEvent)
}