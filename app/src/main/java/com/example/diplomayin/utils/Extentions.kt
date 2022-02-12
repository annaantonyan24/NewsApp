package com.example.diplomayin.utils

import androidx.lifecycle.LifecycleOwner
import com.example.diplomayin.viewmodel.FlowObserver
import kotlinx.coroutines.flow.Flow

inline fun <reified T> Flow<T>.observeInLifecycle(
    lifecycleOwner: LifecycleOwner
) = FlowObserver(lifecycleOwner, this)