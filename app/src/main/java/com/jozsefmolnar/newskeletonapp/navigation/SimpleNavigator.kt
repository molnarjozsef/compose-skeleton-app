package com.jozsefmolnar.newskeletonapp.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SimpleNavigator {

    private val _sharedFlow = MutableSharedFlow<NavigateEvent>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(path: String) {
        _sharedFlow.tryEmit(NavigateEvent.NavigateTo(path))
    }

    fun navigateUp() {
        _sharedFlow.tryEmit(NavigateEvent.NavigateUp)
    }

    sealed interface NavigateEvent {
        object NavigateUp : NavigateEvent
        data class NavigateTo(val route: String) : NavigateEvent
    }
}
