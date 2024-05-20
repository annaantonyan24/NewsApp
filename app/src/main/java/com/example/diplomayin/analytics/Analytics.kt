package com.example.diplomayin.analytics

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import com.example.diplomayin.analytics.AnalyticsConstants.APP_OPENED
import com.example.diplomayin.analytics.AnalyticsConstants.CATEGORY
import com.example.diplomayin.analytics.AnalyticsConstants.DEVICE_ID
import com.example.diplomayin.analytics.AnalyticsConstants.DEVICE_MODEL
import com.example.diplomayin.analytics.AnalyticsConstants.OS_VERSION
import com.example.diplomayin.analytics.AnalyticsConstants.USER_LOGGED_IN
import com.example.diplomayin.analytics.AnalyticsConstants.USER_TAP_CATEGORIES
import com.example.diplomayin.getDeviceName

@SuppressLint("HardwareIds")
internal class AppOpened(context: Context) : AnalyticEvent(
    name = APP_OPENED,
    parameters = mapOf(
        DEVICE_ID to Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        ),
        DEVICE_MODEL to getDeviceName(),
        OS_VERSION to "Android ${Build.VERSION.RELEASE}"
    )
)

@SuppressLint("HardwareIds")
internal class UserTapCategory(context: Context, category: String) : AnalyticEvent(
    name = USER_TAP_CATEGORIES,
    parameters = mapOf(
        DEVICE_ID to Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        ),
        DEVICE_MODEL to getDeviceName(),
        OS_VERSION to "Android ${Build.VERSION.RELEASE}",
        CATEGORY to category
    )
)

@SuppressLint("HardwareIds")
internal class UserLoggedIn(context: Context, email: String) : AnalyticEvent(
    name = USER_LOGGED_IN,
    parameters = mapOf(
        DEVICE_ID to Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        ), DEVICE_MODEL to getDeviceName(), OS_VERSION to "Android ${Build.VERSION.RELEASE}"
    )
)