package com.example.data.database

import androidx.room.*
import com.example.data.model.model.room.NewsDataModel

@Dao
interface NewsDao {
    @Query("select * from newsTable")
    fun getAllNews(): List<NewsDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(item: NewsDataModel)

    @Delete()
    fun deleteData(item: NewsDataModel)
}
