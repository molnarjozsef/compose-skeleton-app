@file:OptIn(ExperimentalCoroutinesApi::class)

package com.jozsefmolnar.newskeletonapp.feature.details

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : BaseViewModel() {

    private var articleId = MutableStateFlow<Int?>(null)

    val article = articleId.filterNotNull()
        .flatMapLatest { newsRepository.getCachedArticle(it) }
        .asStateFlow()

    fun setArticleId(id: Int) {
        viewModelScope.launch {
            articleId.emit(id)
        }
    }
}
