package com.jozsefmolnar.newskeletonapp.feature.settings

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.model.domain.NewsCountry
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import com.jozsefmolnar.newskeletonapp.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val newsRepository: NewsRepository,
) : BaseViewModel() {

    val selectedCountries = settingsRepository.getSelectedCountries().asStateFlow(emptyList())

    fun selectCountry(newsCountry: NewsCountry) {
        viewModelScope.launch {
            settingsRepository.selectCountry(newsCountry)
            newsRepository.fetchLatestNews(forced = true)
        }
    }
}
