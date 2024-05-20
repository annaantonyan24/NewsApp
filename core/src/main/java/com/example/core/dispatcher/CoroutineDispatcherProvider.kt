package com.example.core.dispatcher

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface CoroutineDispatcherProvider {
    val main: CoroutineContext
    val io: CoroutineContext
}

// Mock CoroutineDispatcherProvider implementation for testing
class MockCoroutineDispatcherProvider : CoroutineDispatcherProvider {
    override val main: CoroutineContext
        get() = Dispatchers.Main
    override val io: CoroutineContext
        get() = Dispatchers.IO
}