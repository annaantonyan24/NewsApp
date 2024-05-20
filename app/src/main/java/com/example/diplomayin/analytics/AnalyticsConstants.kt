package com.example.diplomayin.analytics

object AnalyticsConstants {
    const val API_KEY_AMPLITUDE_DEV = "c548f7957d80147cc46a8a4be9a5780a"
    const val API_KEY_AMPLITUDE_PROD = "c548f7957d80147cc46a8a4be9a5780a"

    //actions
    const val APP_OPENED = "app_opened"
    const val USER_LOGGED_IN = "authorization:logg_in.tap"
    const val USER_TAP_CHIPS = "orders:chips.tap"

    const val USER_TAP_CATEGORIES = "user:categories.tap"


    //parameters
    const val DEVICE_ID = "Device ID"
    const val DEVICE_MODEL = "Device Model"
    const val OS_VERSION = "OS Version"
    const val CATEGORY = "Category"
}