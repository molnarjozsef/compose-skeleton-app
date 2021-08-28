package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.action.FooAction
import com.jozsefmolnar.newskeletonapp.store.FooStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    fooStore: FooStore,
    private val fooAction: FooAction,
) : BaseViewModel() {

    val items = fooStore.getFooList()
        .asStateFlow()

    fun fetchLatestFoo() {
        viewModelScope.launch {
            trackProgress {
                fooAction.refreshFooList()
            }
        }
    }

    init {
        fetchLatestFoo()
    }
}
