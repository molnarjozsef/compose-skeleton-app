package com.jozsefmolnar.newskeletonapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jozsefmolnar.newskeletonapp.model.data.WeatherDataModel

@Database(entities = [WeatherDataModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        const val DATABASE_NAME: String = "weather_db"
    }
}
