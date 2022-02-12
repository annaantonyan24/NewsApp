package com.example.domain.di

import com.example.core.dispatcher.BaseCoroutineDispatcherProvider
import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.domain.interactors.NewsInteractor
import com.example.domain.usecases.NewsUseCase
import com.example.domain.usecases.PopularNewsUseCase
import org.koin.dsl.module


val interactorModule = module {
    single<CoroutineDispatcherProvider> { BaseCoroutineDispatcherProvider() }
    factory <NewsInteractor> {NewsUseCase(get(),get())}
    factory <NewsInteractor> { PopularNewsUseCase(get(),get()) }
}
