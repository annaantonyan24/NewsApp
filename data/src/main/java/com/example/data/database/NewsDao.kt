package com.example.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.model.room.NewsDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("select * from newsTable")
    fun getAllNews(): LiveData<List<NewsDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(item: NewsDataModel)
}
