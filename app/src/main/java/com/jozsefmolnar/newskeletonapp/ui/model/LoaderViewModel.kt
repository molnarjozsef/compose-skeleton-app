package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoaderViewModel : ViewModel() {
    val fullScreenLoader = Loader()
    val inlineLoader = Loader()
}

class Loader {
    private val mutableState = MutableStateFlow(LoaderState.HIDE)
    val state = mutableState.asStateFlow()

    fun show() {
        mutableState.value = LoaderState.SHOW
    }

    fun hide() {
        mutableState.value = LoaderState.HIDE
    }
}

enum class LoaderState {
    SHOW, HIDE
}
