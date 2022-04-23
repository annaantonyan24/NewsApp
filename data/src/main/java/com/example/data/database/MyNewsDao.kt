package com.example.data.database

import androidx.room.*
import com.example.data.model.model.room.MyNewsDataModel

@Dao
interface MyNewsDao {

    @Query("select * from myNewsTable")
    fun getMyNews(): List<MyNewsDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyNews(item: MyNewsDataModel)

    @Delete
    fun deleteMyNews(item: MyNewsDataModel)

    @Update
    fun updateMyNews(item: MyNewsDataModel)
}
