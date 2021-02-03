package com.jozsefmolnar.newskeletonapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jozsefmolnar.newskeletonapp.model.data.FooDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FooDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataModel: FooDataModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dataModels: List<FooDataModel>)

    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<FooDataModel>>

    @Query("DELETE FROM items")
    suspend fun clearAll()
}
