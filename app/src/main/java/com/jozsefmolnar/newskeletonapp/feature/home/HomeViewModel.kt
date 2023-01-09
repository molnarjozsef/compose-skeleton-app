package com.jozsefmolnar.newskeletonapp.feature.home

import android.location.Location
import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import com.jozsefmolnar.newskeletonapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val weatherRepository: WeatherRepository,
) : BaseViewModel() {

    val articles = newsRepository.getCachedNews().asStateFlow()

    val weather = weatherRepository.getCachedWeather().asStateFlow()

    suspend fun refreshWeather(location: Location) {
        weatherRepository.fetchWeather(location)
    }

    suspend fun refreshNews() {
        newsRepository.fetchLatestNews()
    }
}
