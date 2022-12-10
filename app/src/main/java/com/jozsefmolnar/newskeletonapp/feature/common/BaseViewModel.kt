package com.jozsefmolnar.newskeletonapp.feature.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

abstract class BaseViewModel : ViewModel() {

    private val progressCount = MutableStateFlow(0)

    val progress = progressCount.map { it > 0 }.asStateFlow(false)

    protected suspend fun trackProgress(action: suspend () -> Unit) {
        try {
            progressCount.value = progressCount.value + 1

            action.invoke()
        } finally {
            progressCount.value = progressCount.value - 1
        }
    }

    protected fun <T> Flow<T>.asStateFlow(): StateFlow<T?> =
        stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    protected fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T> =
        stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue)
}
