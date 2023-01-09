package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import com.jozsefmolnar.newskeletonapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val weatherRepository: WeatherRepository,
) : BaseViewModel() {

    val articles = newsRepository.getCachedNews().asStateFlow()

    val weather = weatherRepository.getCachedWeather().asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            weatherRepository.fetchWeather()
        }
        viewModelScope.launch {
            newsRepository.fetchLatestNews()
        }
    }
}
