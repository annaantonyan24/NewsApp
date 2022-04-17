package com.example.data.model.model.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "myNewsTable")
@Parcelize
data class MyNewsDataModel(
    @PrimaryKey(autoGenerate = true)
    var myNewsID: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,
    @ColumnInfo(name = "content")
    val content: String
): Parcelable
