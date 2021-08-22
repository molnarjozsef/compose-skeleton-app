package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    val items = repository.getCachedItems()
        .asStateFlow()

    fun fetchLatestFoo() {
        viewModelScope.launch {
            trackProgress {
                repository.fetchLatestFoo()
            }
        }
    }

    init {
        fetchLatestFoo()
    }
}
