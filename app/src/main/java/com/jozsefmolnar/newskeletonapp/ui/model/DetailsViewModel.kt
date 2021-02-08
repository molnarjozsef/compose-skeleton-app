package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article>
        get() = _article

    var articleId = MutableStateFlow<Int?>(null)

    fun setArticleId(id: Int) {
        viewModelScope.launch {
            articleId.emit(id)
        }
    }

    init {
        viewModelScope.launch {
            articleId
                .filter { it != null }
                .flatMapLatest {
                    repository.getCachedArticle(it!!)
                }
                .filter { it != null }
                .collect { _article.value = it }
        }
    }
}
