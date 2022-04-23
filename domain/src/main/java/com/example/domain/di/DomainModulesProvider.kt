package com.example.domain.di

import com.example.core.dispatcher.BaseCoroutineDispatcherProvider
import com.example.core.dispatcher.CoroutineDispatcherProvider
import com.example.domain.interactors.*
import com.example.domain.usecases.*
import org.koin.dsl.module


val interactorModule = module {
    single<CoroutineDispatcherProvider> { BaseCoroutineDispatcherProvider() }
    factory <NewsInteractor> {NewsUseCase(get(),get())}
    factory <HealthNewsInteractor> {HealthNewsUseCase(get(),get())}
    factory <TechnologyNewsInteractor> {TechnologyNewsUseCase(get(),get())}
    factory <SearchedNewsInteractor> { SearchedNewsUseCase(get(),get()) }
    factory <GetSavedNewsInteractor> { GetSavedNewsUseCase(get(),get()) }
    factory <InsertNewsInteractor> { InsertNewsUseCase(get(),get()) }
    factory <DeleteNewsInteractor> { DeleteNewsUseCase(get(),get()) }
    factory <InsertMyNewsInteractor> { InsertMyNewsUseCase(get(),get()) }
    factory <GetMyNewsInteractor> { GetMyNewsUseCase(get(),get()) }
    factory <DeleteMyNewsInteractor> { DeleteMyNewsUseCase(get(),get()) }
    factory <UpdateMyNewsInteractor> { UpdateMyNewsUseCase(get(),get()) }
}
