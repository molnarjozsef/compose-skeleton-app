package com.jozsefmolnar.newskeletonapp.repository

import com.jozsefmolnar.newskeletonapp.db.WeatherDao
import com.jozsefmolnar.newskeletonapp.model.domain.Weather
import com.jozsefmolnar.newskeletonapp.service.WeatherService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import timber.log.Timber
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao,
) {

    fun getCachedWeather(): Flow<Weather?> = weatherDao.get()
        .mapNotNull { it.firstOrNull() }
        .map { it.mapToDomainModel() }

    suspend fun fetchWeather() {
        try {
            val weatherApiModel = weatherService.getWeather()

            val weather = weatherApiModel.mapToDomainModel()
            val weatherDataModel = weather.mapToDataModel()

            weatherDao.insert(weatherDataModel)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
