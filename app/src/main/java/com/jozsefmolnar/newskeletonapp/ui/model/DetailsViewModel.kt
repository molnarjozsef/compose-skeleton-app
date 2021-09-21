package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.repository.MainRepository
import com.jozsefmolnar.newskeletonapp.route.DetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseScreenViewModel() {

    private val articleId = currentRouteFlow<DetailsRoute>()
        .map { it.articleId }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )

    val article = articleId.filterNotNull()
        .flatMapLatest { repository.getCachedArticle(it) }
        .asStateFlow()

}
