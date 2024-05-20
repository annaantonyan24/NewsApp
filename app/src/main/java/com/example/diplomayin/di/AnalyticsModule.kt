package com.example.diplomayin.di

import com.example.diplomayin.analytics.Analytic
import com.example.diplomayin.analytics.AnalyticImpl
import com.example.diplomayin.analytics.AnalyticsConstants.API_KEY_AMPLITUDE_DEV
import com.example.diplomayin.analytics.AnalyticsConstants.API_KEY_AMPLITUDE_PROD
import com.example.diplomayin.analytics.ChangedApiKey
import com.example.domain.BuildConfig
import org.koin.dsl.module

val appModule = module {
    single<Analytic> {
        AnalyticImpl(
            get(), when (ChangedApiKey.isProdAnalytics) {
                null -> {
                    if (BuildConfig.DEBUG) API_KEY_AMPLITUDE_DEV else API_KEY_AMPLITUDE_PROD
                }
                else -> {
                    if (ChangedApiKey.isProdAnalytics == true) API_KEY_AMPLITUDE_PROD else API_KEY_AMPLITUDE_DEV
                }
            }
        )
    }
}