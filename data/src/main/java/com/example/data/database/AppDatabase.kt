package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.model.room.NewsDataModel

@Database(
    entities = [NewsDataModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val newsDao: NewsDao
}
