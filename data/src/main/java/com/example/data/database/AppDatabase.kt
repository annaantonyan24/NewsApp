package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.model.model.room.NewsDataModel

@Database(
    entities = [NewsDataModel::class, MyNewsDataModel::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val newsDao: NewsDao
    abstract val myNewsDao: MyNewsDao
}
