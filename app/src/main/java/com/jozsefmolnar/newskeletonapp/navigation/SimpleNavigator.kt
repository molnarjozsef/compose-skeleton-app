package com.jozsefmolnar.newskeletonapp.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SimpleNavigator {

    private val _sharedFlow =
        MutableSharedFlow<String>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(path: String) {
        _sharedFlow.tryEmit(path)
    }

}
