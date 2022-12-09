package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.navigation.Route
import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import com.jozsefmolnar.newskeletonapp.ui.model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val simpleNavigator: SimpleNavigator,
) : BaseViewModel() {

    val items = repository.getCachedNews().asStateFlow()

    init {
        fetchLatestNews()
    }

    fun fetchLatestNews() {
        viewModelScope.launch {
            trackProgress {
                repository.fetchLatestNews()
            }
        }
    }

    fun showDetails(articleId: Int) = simpleNavigator.navigateTo(Route.DetailsRoute.withArgs(articleId))
}
