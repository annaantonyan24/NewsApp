package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val author : String?,
    val title : String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val url: String?,
    val content: String?
):Parcelable