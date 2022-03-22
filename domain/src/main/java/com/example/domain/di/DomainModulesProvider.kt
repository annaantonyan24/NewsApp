package com.example.domain.di

import com.example.core.dispatcher.BaseCoroutineDispatcherProvider
import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.domain.interactors.TechnologyNewsInteractor
import com.example.domain.interactors.NewsInteractor
import com.example.domain.interactors.HealthNewsInteractor
import com.example.domain.interactors.SearchedNewsInteractor
import com.example.domain.usecases.TechnologyNewsUseCase
import com.example.domain.usecases.NewsUseCase
import com.example.domain.usecases.HealthNewsUseCase
import com.example.domain.usecases.SearchedNewsUseCase
import org.koin.dsl.module


val interactorModule = module {
    single<CoroutineDispatcherProvider> { BaseCoroutineDispatcherProvider() }
    factory <NewsInteractor> {NewsUseCase(get(),get())}
    factory <HealthNewsInteractor> {HealthNewsUseCase(get(),get())}
    factory <TechnologyNewsInteractor> {TechnologyNewsUseCase(get(),get())}
    factory <SearchedNewsInteractor> { SearchedNewsUseCase(get(),get()) }
}
