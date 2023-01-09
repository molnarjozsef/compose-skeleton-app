package com.jozsefmolnar.newskeletonapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jozsefmolnar.newskeletonapp.model.data.WeatherDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataModel: WeatherDataModel): Long

    @Query("SELECT * FROM weather")
    fun get(): Flow<List<WeatherDataModel>>
}
