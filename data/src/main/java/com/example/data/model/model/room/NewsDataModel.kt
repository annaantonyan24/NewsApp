package com.example.data.model.model.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "newsTable")
data class NewsDataModel(
    @PrimaryKey(autoGenerate = true)
    var newsID: Int = 0,
    @ColumnInfo(name = "author")
    val author : String?,
    @ColumnInfo(name = "title")
    val title : String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "isSaved")
    var isSaved: Boolean
):Parcelable
