package com.jozsefmolnar.newskeletonapp.feature.details

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import com.jozsefmolnar.newskeletonapp.ui.model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : BaseViewModel() {

    private var articleId = MutableStateFlow<Int?>(null)

    val article = articleId.filterNotNull()
        .flatMapLatest { repository.getCachedArticle(it) }
        .asStateFlow()

    fun setArticleId(id: Int) {
        viewModelScope.launch {
            articleId.emit(id)
        }
    }
}
