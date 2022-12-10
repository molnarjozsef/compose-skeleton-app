package com.jozsefmolnar.newskeletonapp.feature.home

import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.navigation.Route
import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsRepository: NewsRepository,
    private val simpleNavigator: SimpleNavigator,
) : BaseViewModel() {

    val items = newsRepository.getCachedNews().asStateFlow()

    fun showDetails(articleId: Int) = simpleNavigator.navigateTo(Route.DetailsRoute.withArgs(articleId))
}
