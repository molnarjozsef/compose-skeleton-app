package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import com.jozsefmolnar.newskeletonapp.navigation.Route
import com.jozsefmolnar.newskeletonapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val simpleNavigator: SimpleNavigator
) : BaseViewModel() {

    val items = repository.getCachedNews().asStateFlow()

    fun fetchLatestNews() {
        viewModelScope.launch {
            trackProgress {
                repository.fetchLatestNews()
            }
        }
    }

    fun showDetails(articleId: Int) = simpleNavigator.navigateTo(Route.DetailsRoute.withArgs(articleId))

    init {
        fetchLatestNews()
    }
}
