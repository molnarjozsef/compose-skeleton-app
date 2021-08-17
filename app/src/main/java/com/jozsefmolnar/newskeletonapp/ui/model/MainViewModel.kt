package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    private val _items = MutableLiveData<List<Article>>()
    val items: LiveData<List<Article>>
        get() = _items

    private fun initLatestNews() {
        viewModelScope.launch {
            trackProgress {
                repository.fetchLatestNews()
            }
        }
    }

    fun fetchLatestNews() {
        viewModelScope.launch {
            trackProgress {
                repository.fetchLatestNews()
            }
        }
    }

    init {
        initLatestNews()

        viewModelScope.launch {
            repository.getCachedNews()
                .collect { _items.value = it }
        }
    }
}
