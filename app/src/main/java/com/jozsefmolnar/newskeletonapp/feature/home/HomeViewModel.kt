package com.jozsefmolnar.newskeletonapp.feature.home

import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import com.jozsefmolnar.newskeletonapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val weatherRepository: WeatherRepository,
) : BaseViewModel() {

    val articles = newsRepository.getCachedNews().asStateFlow()

    val weather = weatherRepository.getCachedWeather().asStateFlow()

    suspend fun refresh() {
        weatherRepository.fetchWeather()
        newsRepository.fetchLatestNews()

        // We need to add some delay, because if both calls are optimized out,
        // the pull-refresh indicator will get stuck due to Material 3 incompatibility
        @Suppress("MagicNumber")
        delay(300)
    }
}
