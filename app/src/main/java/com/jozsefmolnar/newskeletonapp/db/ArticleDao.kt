package com.jozsefmolnar.newskeletonapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jozsefmolnar.newskeletonapp.model.data.ArticleDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataModel: ArticleDataModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dataModels: List<ArticleDataModel>)

    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<ArticleDataModel>>

    @Query("DELETE FROM articles")
    suspend fun clearAll()
}
