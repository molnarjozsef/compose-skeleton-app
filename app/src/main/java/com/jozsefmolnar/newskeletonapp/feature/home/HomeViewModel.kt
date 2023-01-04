package com.jozsefmolnar.newskeletonapp.feature.home

import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : BaseViewModel() {

    val items = newsRepository.getCachedNews().asStateFlow()

    suspend fun refreshNews() = newsRepository.fetchLatestNews()
}
