package com.example.diplomayin

import android.annotation.SuppressLint
import android.content.*
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import kotlinx.coroutines.*
import java.util.*

fun getDeviceName(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    return if (model.lowercase(Locale.getDefault())
            .startsWith(manufacturer.lowercase(Locale.getDefault()))
    ) {
        model
    } else {
        "$manufacturer $model"
    }
}
