package com.jozsefmolnar.newskeletonapp.repository

import android.location.Location
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.jozsefmolnar.newskeletonapp.db.PreferencesKeys
import com.jozsefmolnar.newskeletonapp.db.WeatherDao
import com.jozsefmolnar.newskeletonapp.model.domain.Weather
import com.jozsefmolnar.newskeletonapp.service.WeatherService
import com.jozsefmolnar.newskeletonapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import timber.log.Timber
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao,
    private val dataStore: DataStore<Preferences>,
) {

    fun getCachedWeather(): Flow<Weather?> = weatherDao.get()
        .mapNotNull { it.firstOrNull() }
        .map { it.mapToDomainModel() }

    suspend fun fetchWeather(location: Location) {
        val lastRefreshedTime = dataStore.data.map { it[PreferencesKeys.WeatherLastRefreshTime] }.first() ?: 0
        if (System.currentTimeMillis() - lastRefreshedTime < Constants.OneHourInMillis) {
            return
        }

        try {
            val weatherApiModel = weatherService.getWeather(
                latitude = location.latitude,
                longitude = location.longitude
            )
            val weather = weatherApiModel.mapToDomainModel()
            val weatherDataModel = weather.mapToDataModel()

            weatherDao.insert(weatherDataModel)

            dataStore.edit { preferences ->
                preferences[PreferencesKeys.WeatherLastRefreshTime] = System.currentTimeMillis()
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
