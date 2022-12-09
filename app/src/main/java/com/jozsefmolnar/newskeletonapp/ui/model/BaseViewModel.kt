package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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
